package com.kofi.app;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Objects;


@RunWith(JUnit4.class)
public class LogEventTest {
    private String id;
    private String state;
    private String timestamp;
    private long timestampMillis;

    @Before
    public void init(){
        id = "abcd";
        state = "STARTED";
        timestamp = "1491377495212";
        timestampMillis = Long.parseLong("1491377495212");
    }

    @Test
    public void testAllFieldsArePopulated(){
        String type = "APPLICATION_LOG";
        String host = "12345";

        LogEvent logEvent = new LogEvent(id, state, timestamp, type, host);

        assert (Objects.equals(logEvent.getId(), id));
        assert (Objects.equals(logEvent.getState(), state));
        assert (Objects.equals(logEvent.getTimestamp(), timestampMillis));
        assert (Objects.equals(logEvent.getType(), type));
        assert (Objects.equals(logEvent.getHost(), host));
    }

    @Test
    public void testAllFieldsArePopulatedExceptTypeAndHost(){
        String type = "";
        String host = "";

        LogEvent logEvent = new LogEvent(id, state, timestamp, type, host);

        assert (Objects.equals(logEvent.getId(), id));
        assert (Objects.equals(logEvent.getState(), state));
        assert (Objects.equals(logEvent.getTimestamp(), timestampMillis));
        assert (Objects.equals(logEvent.getType(), type));
        assert (Objects.equals(logEvent.getHost(), host));
    }
}
