package main.java.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import main.java.Main;
import main.java.dao.ClientDAO;
import main.java.dao.EventDAO;
import main.java.entities.ContratacionPorMes;
import main.java.entities.TopClient;
import main.java.util.ConnectionPool;
import main.java.util.SceneSwitcher;

public class DashBoardController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            previusButton.setDisable(true);

            ClientDAO clientDAO = new ClientDAO(ConnectionPool.getConnection());
            EventDAO eventDAO = new EventDAO(ConnectionPool.getConnection());

            ObservableList<TopClient> topClients = FXCollections.observableArrayList(clientDAO.getTopFiveClients());
            ObservableList<ContratacionPorMes> serviciosPopularesPorMes = FXCollections.observableArrayList(eventDAO.servicesWithGreaterDemand());

            ci.setCellValueFactory(new PropertyValueFactory<TopClient, String>("ci"));
            name.setCellValueFactory(new PropertyValueFactory<TopClient, String>("name"));
            lname.setCellValueFactory(new PropertyValueFactory<TopClient, String>("lname"));
            score.setCellValueFactory(new PropertyValueFactory<TopClient, Integer>("score"));

            
            mes.setCellValueFactory(new PropertyValueFactory<ContratacionPorMes, String>("mes"));
            nombreServicio.setCellValueFactory(new PropertyValueFactory<ContratacionPorMes, String>("nombreServicio"));
            totalContrataciones.setCellValueFactory(new PropertyValueFactory<ContratacionPorMes, Integer>("totalContrataciones"));


            topClientsTableview.setItems(topClients);
            servicioPopularTableview.setItems(serviciosPopularesPorMes);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        
        nextButtom.setOnAction(_ -> {
            try {
                SceneSwitcher.switchPane(centerPaneId, "/main/resources/fxml/DashboardTwo.fxml", "/main/resources/css/DashboardTwo.css", new DasboardTwoController());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


    }

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

    public void loadNewEvent(MouseEvent event) {
        try {
            SceneSwitcher.switchPane(centerPaneId, "/main/resources/fxml/NewEvent.fxml", "/main/resources/css/NewEvent.css", new NewEventController());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cargarDashBoard(MouseEvent event) {
        try {
            Main.switchToDashboard();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private AnchorPane centerPaneId;
    @FXML
    private Button nextButtom;
    @FXML
    private Button previusButton;

    // TopClient
    @FXML
    private TableView<TopClient> topClientsTableview;
    @FXML
    private TableColumn<TopClient, String> ci;
    @FXML
    private TableColumn<TopClient, String>  name;
    @FXML
    private TableColumn<TopClient, String> lname;
    @FXML
    private TableColumn<TopClient, Integer> score;

    // ContratacionPorMes query 
    @FXML
    private TableView<ContratacionPorMes> servicioPopularTableview;
    @FXML
    private TableColumn<ContratacionPorMes, String> mes;
    @FXML
    private TableColumn<ContratacionPorMes, String> nombreServicio;
    @FXML
    private TableColumn<ContratacionPorMes, Integer> totalContrataciones;

}
