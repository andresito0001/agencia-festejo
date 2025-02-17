package main.java.controllers;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.dao.EventDAO;
import main.java.entities.Event;
import main.java.util.ConnectionPool;

public class EventQuerysController implements Initializable {
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        searchButtom.setOnAction(e -> {
            try {
                EventDAO eventDAO = new EventDAO(ConnectionPool.getConnection());
                Date firstDate = Date.valueOf(this.firstDate.getValue());
                Date secondDate = Date.valueOf(this.secondDate.getValue());

                ObservableList<Event> events = FXCollections.observableArrayList(eventDAO.getAllEventsBetween(firstDate, secondDate));

                Set<LocalDate> fechasConflictivas = events.stream()
                    .collect(Collectors.groupingBy(ev -> ev.getEventDate().toLocalDate(),
                            Collectors.mapping(Event::getClientDni, Collectors.toSet())
                    )).entrySet().stream().filter(entry -> entry.getValue().size() > 1)
                      .map(Map.Entry::getKey).collect(Collectors.toSet());

                eventTable.setRowFactory(tv -> new TableRow<Event>() {
                        @Override
                        protected void updateItem(Event event, boolean empty) {
                            super.updateItem(event, empty);
                            if (event == null || empty) {
                                setStyle("");
                            } else {
                                // Verificar si la fecha del evento está en conflicto
                                if (fechasConflictivas.contains(event.getEventDate().toLocalDate())) {
                                    this.getStyleClass().add("conflict-row");
                                } else {
                                    this.getStyleClass().removeAll("conflict-row");
                                }
                            }
                        }
                });

                idEvent.setCellValueFactory(new PropertyValueFactory<Event, Integer>("idEvent"));
                eventName.setCellValueFactory(new PropertyValueFactory<Event, String>("eventName"));
                eventDate.setCellValueFactory(new PropertyValueFactory<Event, Date>("eventDate"));
                eventTime.setCellValueFactory(new PropertyValueFactory<Event, Time>("eventTime"));
                eventLocation.setCellValueFactory(new PropertyValueFactory<Event, String>("eventLocation"));
                clientDni.setCellValueFactory(new PropertyValueFactory<Event, String>("clientDni"));
                idService.setCellValueFactory(new PropertyValueFactory<Event, Integer>("idService"));

                eventTable.setItems(events);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


        
    }

    

    @FXML
    private DatePicker firstDate;
    @FXML
    private DatePicker secondDate;
    @FXML
    private Button searchButtom;
    @FXML
    private TableView<Event> eventTable;
    @FXML
    private TableColumn<Event, Integer> idEvent;
    @FXML
    private TableColumn<Event, String> eventName;
    @FXML
    private TableColumn<Event, Date> eventDate;
    @FXML
    private TableColumn<Event, Time> eventTime;
    @FXML
    private TableColumn<Event, String> eventLocation;
    @FXML
    private TableColumn<Event, String> clientDni;
    @FXML
    private TableColumn<Event, Integer> idService;

}
