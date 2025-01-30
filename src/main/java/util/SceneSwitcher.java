package main.java.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SceneSwitcher {
    public static void switchScene(Stage stage, String fxmlPath, String cssPath, Object controller) {
        try {
            Parent root = loadFXML(fxmlPath, controller);
            Scene scene = createScene(root, cssPath);
            stage.setTitle("AGENCIA DE FESTEJO UDO - ANZ");
            // stage.getIcons().add(new Image(SceneSwitcher.class.getResourceAsStream("/main/resources/img/nak-logo.png")));

            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Parent loadFXML(String fxmlPath, Object controller) throws Exception {
        FXMLLoader loader = new FXMLLoader(SceneSwitcher.class.getResource(fxmlPath));
        if (controller != null) {
            loader.setController(controller);
        }
        loader.setController(controller);
        return loader.load();
    }

    private static Scene createScene(Parent root, String cssPath) {
        Scene scene = new Scene(root);
        String css = SceneSwitcher.class.getResource(cssPath).toExternalForm();
        scene.getStylesheets().add(css);
        return scene;
    }

    public static void switchPane(Pane pane, String fxmlPath, String cssPath, Object controller) throws Exception {
        Parent root = SceneSwitcher.loadFXML(fxmlPath, controller);
        pane.getChildren().clear();
        pane.getChildren().add(root);
        root.applyCss();
    }
}