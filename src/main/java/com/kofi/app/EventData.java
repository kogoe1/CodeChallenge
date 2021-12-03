package com.kofi.app;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EventData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long id;
    private String eventId;
    private  long duration;
    private  String type;
    private  String host;

    private static final long DURATION_THRESHOLD_MILLIS = 4;

    //Added for JPA use
    protected EventData(){}

    public EventData(String eventId, long duration, String type, String host) {
        this.eventId = eventId;
        this.duration = duration;
        this.type = type;
        this.host = host;
    }

    public Long getId() {
        return id;
    }

    public long getDuration() {
        return duration;
    }

    public String getType() {
        return type;
    }

    public String getHost() {
        return host;
    }

    public String getEventId() {
        return eventId;
    }

    public boolean getIsAlerted() {
        return duration > DURATION_THRESHOLD_MILLIS;
    }

//    public void setId(long id) {
//        this.id = id;
//    }
}
