package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import main.java.entities.Client;
import main.java.entities.TopClient;

public class ClientDAO {
    public ClientDAO(final Connection conn) {
        this.conn = conn;
    }

    public void insertClient(final Client client) throws SQLException {
        final String query = "insert into clientes(cedula, nombre, apellido, direccion, telefono, correo_electronico) values (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement st = conn.prepareStatement(query)) {
            st.setString(1, client.getCi());
            st.setString(2, client.getName());
            st.setString(3, client.getLastName());
            st.setString(4, client.getDirection());
            st.setString(5, client.getPhoneNumber());
            st.setString(6, client.getMail());
            
            st.executeUpdate();
            st.close();
        }
    }

    public List<Client> getClientsBy(final String condition) throws SQLException {
        final String query = "select * from clientes " + condition;
        List<Client> clients = new ArrayList<>();

        try (final PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery()) {
            
            while (rs.next()) {
                clients.add(new Client(rs.getString("cedula"),
                rs.getString("nombre"), rs.getString("apellido"),
                rs.getString("direccion"), rs.getString("telefono"),
                rs.getString("correo_electronico")));
            }
        }
        
        return clients;
    }

    public TopClient instanceTopClient(final String key, final Integer score) throws SQLException {
        final String sql  = "select * from clientes where cedula = " + "'" + key  + "'";
       
        try (final PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new TopClient(rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        score
                    );
                }
        }

        return null;
    }


    public List<TopClient> getTopFiveClients() throws SQLException {
        final String query = """
            SELECT 
                cliente,
                COUNT(id_evento) AS total_eventos
            FROM eventos
            GROUP BY cliente
            ORDER BY total_eventos DESC
            LIMIT 5;
        """;

        List<TopClient> clients = new ArrayList<>();

        try (PreparedStatement st = conn.prepareStatement(query); 
        ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                clients.add(instanceTopClient(rs.getString("cliente"), Integer.parseInt(rs.getString("total_eventos"))));
            }
        }

        return clients;
    }

    private final Connection conn;
}
