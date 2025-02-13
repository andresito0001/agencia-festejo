package main.java.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import main.java.entities.Event;

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

    private Connection connection;
}
