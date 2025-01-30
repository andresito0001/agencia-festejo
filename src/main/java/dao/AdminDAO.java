package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {
    public AdminDAO(final Connection conn) {
        this.conn = conn;
    }

    public boolean authenticateUser(final String userName, final String password) throws SQLException {
        final String query = "select * from administradores where nombre_usuario = ? and password = ?";
        
        try (final PreparedStatement st = this.conn.prepareStatement(query)) {
            st.setString(1, userName);
            st.setString(2, password);

            try (final ResultSet rs = st.executeQuery()) {
                return rs.next();
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private final Connection conn;
}
