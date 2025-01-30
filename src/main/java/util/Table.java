package main.java.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Table {
    public Table(final Connection conn) {
        this.conn = conn;
    }
    
    public static void createTable(final Connection conn, final String name, final List<String> colums,
                                    final List<String> types) throws SQLException {
        StringBuilder sql = new StringBuilder("create table " + name + " (");

        for (int i = 0; i < colums.size(); i++) {
            sql.append(colums.get(i)).append(" ").append(types.get(i));
                if (i < colums.size() - 1) {
                sql.append(", ");
            }
        }
        sql.append(");");

        try (final PreparedStatement st = conn.prepareStatement(sql.toString())) {
            st.executeUpdate();
            st.close();
        }
    }
    
    public void createTable(final String name, final List<String> colums,
                                    final List<String> types) throws SQLException {
        StringBuilder sql = new StringBuilder("create table " + name + " (");

        for (int i = 0; i < colums.size(); i++) {
            sql.append(colums.get(i)).append(" ").append(types.get(i));
                if (i < colums.size() - 1) {
                sql.append(", ");
            }
        }
        sql.append(");");

        try (final PreparedStatement st = conn.prepareStatement(sql.toString())) {
            st.executeUpdate();
            st.close();
        }
    }

    private final Connection conn;
}
