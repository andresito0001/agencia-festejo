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
import main.java.dao.ClientDAO;
import main.java.dao.EventDAO;
import main.java.entities.ContratacionPorMes;
import main.java.entities.EventRank;
import main.java.entities.TopClient;
import main.java.util.ConnectionPool;
import main.java.util.SceneSwitcher;

public class DasboardThreeController implements Initializable  {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nextButtom.setDisable(true);

        try {
            EventDAO eventDAO = new EventDAO(ConnectionPool.getConnection());
            ObservableList<EventRank> peekAndLowSeasons = FXCollections.observableArrayList(eventDAO.getPeekAndLowSeasonEventsCategory());

            enevntType.setCellValueFactory(new PropertyValueFactory<EventRank, String>("enevntType"));
            peekSeason.setCellValueFactory(new PropertyValueFactory<EventRank, String>("peekSeason"));
            lowSeason.setCellValueFactory(new PropertyValueFactory<EventRank, String>("lowSeason"));

            tempAltaBajaTableview.setItems(peekAndLowSeasons);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        
        previusButton.setOnAction(_ -> {
            try {
                SceneSwitcher.switchPane(centerPaneId, "/main/resources/fxml/DashboardTwo.fxml", "/main/resources/css/DashboardTwo.css", new DasboardTwoController());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    @FXML
    private Button nextButtom;
    @FXML
    private Button previusButton;
    @FXML
    private AnchorPane centerPaneId;

    @FXML
    private TableView<EventRank> tempAltaBajaTableview;
    @FXML
    private TableColumn<EventRank, String> enevntType;
    @FXML
    private TableColumn<EventRank, String> peekSeason;
    @FXML
    private TableColumn<EventRank, String> lowSeason;
    
    
}
