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

    public void cargarConsutarEventos(MouseEvent event) {
        try {
            centerPaneId.getStylesheets().add(getClass().getResource("/main/resources/css/EventQuerys.css").toExternalForm());
            SceneSwitcher.switchPane(centerPaneId, "/main/resources/fxml/EventQuerys.fxml", "/main/resources/css/EventQuerys.css", new EventQuerysController());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("cargar consultar eventos...");
    }

    public void loadClientReport(MouseEvent event) {
        try {
            SceneSwitcher.switchPane(centerPaneId, "/main/resources/fxml/ClientsReport.fxml", "/main/resources/css/ClientsReport.css", new ClientsReportController());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cargarnuevoUsuario(MouseEvent event) {
        try {
            SceneSwitcher.switchPane(centerPaneId, "/main/resources/fxml/NuevoUsuario.fxml", "/main/resources/css/NuevoUsuario.css", new NuevoUsuarioController());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private AnchorPane centerPaneId;
}
