package main.java.controllers;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import main.java.dao.EmpleadoDAO;
import main.java.entities.Empleado;
import main.java.util.ConnectionPool;

public class RegistrarEmpleadoController {

    public void initialize() {
        cargocb.getItems().addAll("Organizador de eventos", "Coordinador de eventos", "Diseñador de eventos", "Gerente de ventas");
    }

    public void registrarCliente() throws SQLException {
        Empleado empleado = new Empleado (
            nombretf.getText(),
            apellidotf.getText(),
            cargocb.getValue(),
            Double.parseDouble(salariotf.getText())
        );

        EmpleadoDAO empleadoDAO = new EmpleadoDAO(ConnectionPool.getConnection());
        empleadoDAO.insertEmpleado(empleado);

        limpiarCampos();
    }

    private void limpiarCampos() {
        nombretf.clear();
        apellidotf.clear();
        cargocb.setValue(null);
        salariotf.clear();
    }

    @FXML
    private TextField nombretf;
    @FXML
    private TextField apellidotf;
    @FXML
    private ComboBox<String> cargocb;
    @FXML
    private TextField salariotf;
}
