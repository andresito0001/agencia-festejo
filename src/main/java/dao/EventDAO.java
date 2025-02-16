package main.java.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import main.java.entities.AverageEventType;
import main.java.entities.ContratacionPorMes;
import main.java.entities.Event;
import main.java.entities.EventRank;
import main.java.entities.PopularService;
import main.java.util.ConnectionPool;

public class EventDAO {
    public EventDAO(final Connection connection) {
        this.connection = connection;
    }

    public List<Event> getAllEventsBetween(Date firstDate, Date secondDate) {
        String sql = "select * from eventos where fecha_evento between ? and ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, firstDate);
            statement.setDate(2, secondDate);

            ResultSet resultSet = statement.executeQuery();
            List<Event> events = new ArrayList<>();

            while (resultSet.next()) {
                Event event = new Event(
                    resultSet.getInt("id_evento"),
                    resultSet.getString("nombre_evento"),
                    resultSet.getDate("fecha_evento"),
                    resultSet.getTime("hora_evento"),
                    resultSet.getString("lugar_evento"),
                    resultSet.getString("cliente"),
                    resultSet.getInt("id_servicio")
                );

                events.add(event);
            }

            return events;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    public List<PopularService> getPopularServiceAsList() throws SQLException {
        final String sql = """
            WITH servicios_populares AS (
                SELECT 
                    s.categoria AS tipo_evento,
                    s.nombre AS nombre_servicio,
                    COUNT(e.id_evento) AS total_contrataciones,
                    ROW_NUMBER() OVER (
                        PARTITION BY s.categoria 
                        ORDER BY COUNT(e.id_evento) DESC
                    ) AS ranking
                FROM eventos e
                INNER JOIN servicios s ON e.id_servicio = s.id_servicio
                GROUP BY s.categoria, s.nombre
            )
            SELECT 
                tipo_evento,
                nombre_servicio,
                total_contrataciones
            FROM servicios_populares
            WHERE ranking = 1;
        """;

        List<PopularService> resultados = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String tipoEvento = rs.getString("tipo_evento");
                String servicio = rs.getString("nombre_servicio");
                int contrataciones = rs.getInt("total_contrataciones");
                
                resultados.add(new PopularService(tipoEvento, servicio, contrataciones));
            }
        }
        return resultados;
    }

    public List<AverageEventType> getAvgByEventType() throws SQLException {
        final String sql = """
            SELECT 
                s.categoria AS tipo_evento,
                ROUND(CAST(COALESCE(AVG(s.precio), 0) AS NUMERIC), 2) AS precio_promedio,
                COALESCE(MIN(s.precio), 0) AS precio_minimo,
                COALESCE(MAX(s.precio), 0) AS precio_maximo
            FROM servicios s
            LEFT JOIN eventos e ON s.id_servicio = e.id_servicio
            GROUP BY s.categoria
            ORDER BY s.categoria;
        """;

        List<AverageEventType> result = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String eventType = rs.getString("tipo_evento");
                    Double avgPrince = rs.getDouble("precio_promedio");
                    Double minAvg = rs.getDouble("precio_minimo");
                    Double maxAvg = rs.getDouble("precio_maximo");

                    result.add(new AverageEventType(eventType, avgPrince,minAvg, maxAvg));
                }
        }

        return result;
    }


    public List<EventRank> getPeekAndLowSeasonEventsCategory() throws SQLException {
        final String sql = """
            WITH eventos_por_mes AS (
        SELECT 
            s.categoria,
            EXTRACT(MONTH FROM e.fecha_evento) AS mes,
            COUNT(e.id_evento) AS total_eventos,
            RANK() OVER (
                PARTITION BY s.categoria 
                ORDER BY COUNT(e.id_evento) DESC
            ) AS ranking_alta,
            RANK() OVER (
                PARTITION BY s.categoria 
                ORDER BY COUNT(e.id_evento) ASC
            ) AS ranking_baja
        FROM servicios s
        LEFT JOIN eventos e ON s.id_servicio = e.id_servicio
        GROUP BY s.categoria, mes
    ),
    todas_categorias AS (
        SELECT DISTINCT categoria FROM servicios
    )
    SELECT 
        tc.categoria AS tipo_evento,
        MAX(CASE WHEN epm.ranking_alta = 1 THEN epm.mes END) AS mes_temporada_alta,
        MAX(CASE WHEN epm.ranking_baja = 1 THEN epm.mes END) AS mes_temporada_baja
    FROM todas_categorias tc
    LEFT JOIN eventos_por_mes epm ON tc.categoria = epm.categoria
    GROUP BY tc.categoria
    ORDER BY tc.categoria;
        """;

        List<EventRank> result = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String tipoEvento = rs.getString("tipo_evento");
                
                // Manejo de meses (enteros o null)
                Object mesAlta = rs.getObject("mes_temporada_alta");
                Object mesBaja = rs.getObject("mes_temporada_baja");
                
                // Convertir a "N/A" si es null
                String mesAltaStr = (mesAlta != null) ? mesAlta.toString() : "N/A";
                String mesBajaStr = (mesBaja != null) ? mesBaja.toString() : "N/A";
                
                result.add(new EventRank(tipoEvento, mesAltaStr, mesBajaStr));
            }
        }

        return result;
    }

    public List<ContratacionPorMes> servicesWithGreaterDemand() throws SQLException {
        final String sql = """
            SELECT 
                EXTRACT(MONTH FROM e.fecha_evento) AS mes,
                s.nombre,
                COUNT(e.id_evento) AS total_contrataciones
            FROM eventos e
            JOIN servicios s ON e.id_servicio = s.id_servicio
            GROUP BY mes, s.nombre
            ORDER BY mes, total_contrataciones DESC;
        """;

        List<ContratacionPorMes> resultados = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int mes = rs.getInt("mes");
                String nombreServicio = rs.getString("nombre");
                int total = rs.getInt("total_contrataciones");

                resultados.add(new ContratacionPorMes(mes, nombreServicio, total));
            }
        }

        return resultados;
    }
    
    private Connection connection;
}
