package main.java.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.entities.Client;

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



    private final Connection conn;
}
