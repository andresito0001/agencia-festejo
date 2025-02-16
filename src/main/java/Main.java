package main.java;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.stage.Stage;
import main.java.controllers.DashBoardController;
import main.java.controllers.LoginController;
import main.java.dao.ClientDAO;
import main.java.dao.EventDAO;
import main.java.util.ConnectionPool;
import main.java.util.DBConstants;
import main.java.util.SceneSwitcher;
import main.java.util.Table;
import main.java.entities.ContratacionPorMes;
import main.java.entities.PopularService;
import main.java.entities.TopClient;

public class Main extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) {
        try {
            primaryStage = stage;
            SceneSwitcher.switchScene(stage, "/main/resources/fxml/login.fxml", "/main/resources/css/login.css", new LoginController());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void switchToDashboard() {
        SceneSwitcher.switchScene(primaryStage, "/main/resources/fxml/dashboard.fxml", "/main/resources/css/dashboard.css", new DashBoardController());
    }

    public static void main(String[] args) throws SQLException {
        launch(args);

    }
}