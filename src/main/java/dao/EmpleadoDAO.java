package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.java.entities.Empleado;

public class EmpleadoDAO {
    public EmpleadoDAO(final Connection conn) {
        this.conn = conn;
    }

    public void insertEmpleado(final Empleado empleado) throws SQLException {
        final String query = "INSERT INTO empleados(nombre, apellido, cargo, salario) VALUES (?, ?, ?, ?)";

        try (final PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, empleado.getName());
            ps.setString(2, empleado.getLastName());
            ps.setString(3, empleado.getCargo());
            ps.setDouble(4, empleado.getSalary());

            ps.executeUpdate();
        }
    }

    private final Connection conn;
}
