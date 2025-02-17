package main.java;

import java.sql.SQLException;
import javafx.application.Application;
import javafx.stage.Stage;
import main.java.controllers.DashBoardController;
import main.java.controllers.LoginController;
import main.java.util.SceneSwitcher;


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