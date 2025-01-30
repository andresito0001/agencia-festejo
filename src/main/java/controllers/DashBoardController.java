package main.java.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import main.java.util.SceneSwitcher;

public class DashBoardController {
    @FXML
    public void cargarRegistrarCliente(MouseEvent event) {
        try {
            SceneSwitcher.switchPane(centerPaneId, "/main/resources/fxml/NewUser.fxml", "/main/resources/css/NewUser.css", new NewClientController());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("cargar nuevo cliente...");
    }

    @FXML
    public void cargarNuevoEmpleado(MouseEvent event) {
        try {
            SceneSwitcher.switchPane(centerPaneId, "/main/resources/fxml/RegistrarEmpleado.fxml", "/main/resources/css/RegistrarEmpleado.css", new RegistrarEmpleadoController());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("cargar nuevo empleado...");
    }

    @FXML
    private AnchorPane centerPaneId;
}
