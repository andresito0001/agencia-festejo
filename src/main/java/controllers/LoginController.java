package main.java.controllers;

import java.sql.Connection;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import main.java.Main;
import main.java.dao.AdminDAO;
import main.java.util.ConnectionPool;

public class LoginController {
    @FXML
    private TextField usernametf;
    @FXML
    private TextField passwordtf;
    @FXML
    private Button loginButtom;
    @FXML
    private Label errorMsg;

    @FXML
    public void loginUser(MouseEvent event) {
        Task<Boolean> loginTask = new Task<>() {
            @Override
            protected Boolean call() throws Exception {
                try (final Connection conn = ConnectionPool.getConnection()) {
                    AdminDAO admin = new AdminDAO(conn);
                    return admin.authenticateUser(usernametf.getText(), passwordtf.getText());
                }
            }
        };

        loginTask.setOnSucceeded(_ -> {
            if (loginTask.getValue()) {
                errorMsg.setText("");
                Main.switchToDashboard();
                System.out.println("Usuario autenticado");
            } else {
                errorMsg.setText("* Usuario o contraseña invalidos");
            }
        });

        loginTask.setOnFailed(_ -> {
            loginTask.getException().printStackTrace();
        });

        new Thread(loginTask).start();
    }
}
