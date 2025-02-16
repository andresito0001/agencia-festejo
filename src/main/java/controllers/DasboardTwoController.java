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
import javafx.scene.layout.AnchorPane;
import main.java.Main;
import main.java.dao.EventDAO;
import main.java.entities.AverageEventType;
import main.java.entities.PopularService;
import main.java.entities.TopClient;
import main.java.util.ConnectionPool;
import main.java.util.SceneSwitcher;

public class DasboardTwoController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            EventDAO eventDAO = new EventDAO(ConnectionPool.getConnection());

            ObservableList<PopularService> popularServices = FXCollections.observableArrayList(eventDAO.getPopularServiceAsList());
            ObservableList<AverageEventType> avgEvents = FXCollections.observableArrayList(eventDAO.getAvgByEventType());
            
            tipoEvento.setCellValueFactory(new PropertyValueFactory<PopularService, String>("tipoEvento"));
            servicio.setCellValueFactory(new PropertyValueFactory<PopularService, String>("servicio"));
            contrataciones.setCellValueFactory(new PropertyValueFactory<PopularService, Integer>("contrataciones"));


            eventType.setCellValueFactory(new PropertyValueFactory<AverageEventType, String>("eventType"));
            avg.setCellValueFactory(new PropertyValueFactory<AverageEventType, Double>("avg"));
            minAvg.setCellValueFactory(new PropertyValueFactory<AverageEventType, Double>("minAvg"));
            maxAvg.setCellValueFactory(new PropertyValueFactory<AverageEventType, Double>("maxAvg"));


            popularServiceTableview.setItems(popularServices);
            avgTableview.setItems(avgEvents);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        previusButton.setOnAction(_ -> {
            Main.switchToDashboard();
        });

        nextButtom.setOnAction(_ -> {
            try {
                SceneSwitcher.switchPane(centerPaneId, "/main/resources/fxml/DasboardThree.fxml", "/main/resources/css/DasboardThree.css", new DasboardThreeController());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


    }
    
    @FXML
    private AnchorPane centerPaneId;
    @FXML
    private Button previusButton;
    @FXML
    private Button nextButtom;


    @FXML
    private TableView<PopularService> popularServiceTableview;
    @FXML
    private TableColumn<PopularService, String> tipoEvento;
    @FXML
    private TableColumn<PopularService, String> servicio;
    @FXML
    private TableColumn<PopularService, Integer> contrataciones;
    

    @FXML
    private TableView<AverageEventType> avgTableview;
    @FXML
    private TableColumn<AverageEventType, String> eventType;
    @FXML
    private TableColumn<AverageEventType, Double> avg;
    @FXML
    private TableColumn<AverageEventType, Double> minAvg;
    @FXML
    private TableColumn<AverageEventType, Double> maxAvg;
}
