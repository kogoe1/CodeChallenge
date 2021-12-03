package com.kofi.app;

public class LogEvent {

    private  String id;
    private  String state;
    private  long timestamp;
    private  String type;
    private  String host;

    public LogEvent(String id, String state, String timestamp, String type, String host) {
        this.id = id;
        this.state = state;
        this.timestamp = Long.parseLong(timestamp);
        this.type = type;
        this.host = host;
    }

    public String getHost() {
        return host;
    }

    public String getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getType() {
        return type;
    }

}
