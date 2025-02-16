package main.java.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.dao.ClientDAO;
import main.java.entities.Client;
import main.java.util.ConnectionPool;

public class ClientsReportController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        serachTypeCombobox.getItems().setAll("Todo", "Nombre", "Apellido", "Cedula", "Telefono", "Correo");
        searchButtom.setOnAction(_ -> {
            if (searchBar == null || searchBar.getText().isEmpty() || serachTypeCombobox == null || serachTypeCombobox.getSelectionModel().isEmpty()) {
                throw new IllegalArgumentException("Search bar cannot be null");
            }
    
            final String selectItemsearchType = serachTypeCombobox.getValue();
            final String searchBarInput = searchBar.getText();
    
            try {
                String queryCondition;
                switch (selectItemsearchType) {
                    case "Nombre":
                        queryCondition = "WHERE nombre LIKE '%" + searchBarInput + "%'";
                        break;
                    case "Apellido":
                        queryCondition = "WHERE apellido LIKE '%" + searchBarInput + "%'";
                        break;
                    case "Cedula":
                        queryCondition = "WHERE cedula = '" + searchBarInput + "'";
                        break;
                    case "Telefono":
                        queryCondition = "WHERE telefono = '" + searchBarInput + "'";
                        break;
                    case "Correo":
                        queryCondition = "WHERE correo_electronico LIKE '%" + searchBarInput + "%'";
                        break;
                    case "Todo":
                    default:
                        queryCondition = ""; // Si selecciona "Todo", no aplica filtro
                        break;
                }
                ObservableList<Client> clients = FXCollections.observableArrayList(
                    new ClientDAO(ConnectionPool.getConnection()).getClientsBy(queryCondition)
                );
                dniColum.setCellValueFactory(new PropertyValueFactory<>("ci"));
                nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                lnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
                locationColumn.setCellValueFactory(new PropertyValueFactory<>("direction"));
                phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
                emailColumn.setCellValueFactory(new PropertyValueFactory<>("mail"));
                clientsTableview.setItems(clients);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    

    @FXML
    private Button searchButtom;
    @FXML
    private TextField searchBar;
    @FXML
    private ComboBox<String> serachTypeCombobox;
    @FXML
    private TableView<Client> clientsTableview;
    @FXML
    private TableColumn<Client, String> dniColum;
    @FXML
    private TableColumn<Client, String> nameColumn;
    @FXML
    private TableColumn<Client, String> lnameColumn;
    @FXML
    private TableColumn<Client, String> locationColumn;
    @FXML
    private TableColumn<Client, String> phoneNumberColumn;
    @FXML
    private TableColumn<Client, String> emailColumn;
}
