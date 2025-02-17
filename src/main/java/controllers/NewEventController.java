package main.java.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import main.java.Main;
import main.java.dao.EventDAO;
import main.java.util.ConnectionPool;

public class NewEventController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EventDAO eventDAO;
        try {
            eventDAO = new EventDAO(ConnectionPool.getConnection());
            List<main.java.entities.Service> sv = eventDAO.getAvServices();
            List<String> svConcat = new ArrayList<>();

            for (var s : sv) {
                svConcat.add(s.getId().concat(" ").concat(s.getServiceName()));
            }

            serviceCombobox.getItems().addAll(svConcat);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        registerButton.setOnAction(_ -> {
            String desc = descTextfield.getText();
            Date date = Date.valueOf(datePicker.getValue());
            String time = timeTextfield.getText();
            String eventLocation = locationTextfield.getText();
            String ci = ciTextfield.getText();
            Integer eventID = Integer.parseInt(serviceCombobox.getValue().split(" ")[0]);
            
            String sql = "INSERT INTO eventos(nombre_evento, fecha_evento, hora_evento, lugar_evento, cliente, id_servicio) "
           + "VALUES (?, ?, ?, ?, ?, ?)"; // 6 parámetros

            try (Connection conn = ConnectionPool.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

                // Asignar valores a los parámetros
                pstmt.setString(1, desc);                 // nombre_evento
                pstmt.setDate(2, date);                   // fecha_evento
                pstmt.setTime(3, Time.valueOf(time));                 // hora_evento
                pstmt.setString(4, eventLocation);        // lugar_evento
                pstmt.setString(5, ci);                   // cliente
                pstmt.setInt(6, eventID);              // id_servicio

                pstmt.executeUpdate();
                System.out.println("Evento insertado correctamente.");

                Main.switchToDashboard();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        
    }
    

    @FXML
    private TextField descTextfield;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField timeTextfield;
    @FXML
    private TextField locationTextfield;
    @FXML
    private TextField ciTextfield;
    @FXML
    private ComboBox<String> serviceCombobox; 
    @FXML
    private Button registerButton;

}
