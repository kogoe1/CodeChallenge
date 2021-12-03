package com.kofi.app;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Objects;

@RunWith(JUnit4.class)
public class EventDataTest {
    private String eventId;
    private String type;
    private String host;
    private  int duration = 4;

    @Before
    public void init(){
        eventId = "abcd";
        type = "APPLICATION_LOG";
        host = "12345";
        duration = 4;
    }

    @Test
    public void testHasAllFieldsAndIsAlertingEvent (){
        int duration = 10;

        EventData eventData = new EventData(eventId, duration, type, host);

        assert (Objects.equals(eventData.getEventId(), eventId));
        assert (eventData.getDuration() == duration);
        assert (Objects.equals(eventData.getType(), type));
        assert (Objects.equals(eventData.getHost(), host));
        assert (eventData.getIsAlerted());
    }

    @Test
    public void testHasAllFieldsAndIsNotAlertingEvent (){
        EventData eventData = new EventData(eventId, duration, type, host);

        assert (eventData.getEventId().equals(eventId));
        assert (eventData.getDuration() == duration);
        assert (eventData.getType().equals(type));
        assert (Objects.equals(eventData.getHost(), host));
        assert (!eventData.getIsAlerted());
    }

    @Test
    public void testEventDataHasNoHostAndType() {
        String type = "";
        String host = "";

        EventData eventData = new EventData(eventId, duration, type, host);

        assert (Objects.equals(eventData.getType(), type));
        assert (Objects.equals(eventData.getHost(), host));
    }
}
