package main.java.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class NuevoUsuarioController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        registerButtom.setOnAction (e -> {
            typeUserCombobox.getItems().setAll("admin", "invitado");

            
        });
    }

    @FXML
    private TextField usernameTextfield;
    @FXML
    private ComboBox<String> typeUserCombobox;
    @FXML
    private PasswordField passroedTextfield;
    @FXML
    private Button registerButtom;
}
