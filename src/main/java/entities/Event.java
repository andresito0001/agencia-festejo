package main.java.entities;

import java.sql.Date;
import java.sql.Time;

public class Event {
    public Event(Integer idEvent, String eventName, Date eventDate, Time eventTime, String eventLocation, String clientDni, Integer idService) {
        this.idEvent = idEvent;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventLocation = eventLocation;
        this.clientDni = clientDni;
        this.idService = idService;
    }

    public String getClientDni() {
        return clientDni;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public String getEventName() {
        return eventName;
    }
    
    public Time getEventTime() {
        return eventTime;
    }

    public Integer getIdEvent() {
        return idEvent;
    }
    
    public Integer getIdService() {
        return idService;
    }

    private Integer idEvent;
    private String eventName;
    private Date eventDate;
    private Time eventTime;
    private String eventLocation;
    private String clientDni;
    private Integer idService;
    
}