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
            if (searchBar == null || searchBar.getText().isEmpty() || serachTypeCombobox == null || serachTypeCombobox.getSelectionModel().isEmpty()) throw new IllegalArgumentException("Search bar cannot be null");

            final String selectItemsearchType = serachTypeCombobox.getValue();
            final  String searchBarInput = searchBar.getText();

            try {
                // clientsList.addAll(new ClientDAO(ConnectionPool.getConnection()).getClientsBy("where " + selectItemsearchType.toLowerCase() + " = " + "'" + searchBarInput + "'"));
                ObservableList<Client> clients = FXCollections.observableArrayList(new ClientDAO(ConnectionPool.getConnection()).getClientsBy("where " + selectItemsearchType.toLowerCase() + " = " + "'" + searchBarInput + "'"));

                dniColum.setCellValueFactory(new PropertyValueFactory<Client, String>("ci"));
                nameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));
                lnameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("lastName"));
                locationColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("direction"));
                phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("phoneNumber"));
                emailColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("mail"));
                
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
