package main.java.controllers;

import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.java.dao.ClientDAO;
import main.java.entities.Client;
import main.java.util.ConnectionPool;

public class NewClientController {
    public void registrarCliente() throws SQLException {
        Client cliente = new Client (
            cedulatf.getText(),
            nombretf.getText(),
            apellidotf.getText(),
            direcciontf.getText(),
            telefonotf.getText(),
            correotf.getText()
        );

        ClientDAO clientDAO = new ClientDAO(ConnectionPool.getConnection());
        clientDAO.insertClient(cliente);

        limpiarCampos();
    }

    public void limpiarCampos() {
        nombretf.clear();
        apellidotf.clear();
        telefonotf.clear();
        correotf.clear();
        direcciontf.clear();
        cedulatf.clear();
    }

    @FXML
    private TextField nombretf;
    @FXML
    private TextField apellidotf;
    @FXML
    private TextField telefonotf;
    @FXML
    private TextField correotf;
    @FXML
    private TextField direcciontf;
    @FXML
    private TextField cedulatf;
    @FXML
    private Button botonRegistrar;
    @FXML
    private Label infoMsg;
}
