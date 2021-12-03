package demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
//import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class ProcessLogEventTest {
    private List<LogEvent> logEvents;

    @Before
    public void init() {
        logEvents = new ArrayList<>();
        logEvents.add(new LogEvent("scsmbstgra", "STARTED", "1491377495212", "APPLICATION_LOG", "12345"));
        logEvents.add(new LogEvent("scsmbstgrb", "STARTED", "1491377495213", "", ""));
        logEvents.add(new LogEvent("scsmbstgrc", "FINISHED", "1491377495218", "", ""));
        logEvents.add(new LogEvent("scsmbstgra", "FINISHED", "1491377495217", "APPLICATION_LOG", "12345"));
        logEvents.add(new LogEvent("scsmbstgrc", "STARTED", "1491377495210", "", ""));
        logEvents.add(new LogEvent("scsmbstgrb", "FINISHED", "1491377495216", "", ""));
    }

    @Test
    public void testNonEmptyEventListIsReturned() throws IOException {
//        String filename = "/Users/kofiogoe/kogoe1/dev/gradle_example/src/main/resources/logfile.txt";
//        LogParser logParser = new LogParser(filename);
//        List<LogEvent> logEvents = logParser.parseJSON();
        ProcessLogEvent processLogEvent = new ProcessLogEvent(logEvents);

        int eventListSize = processLogEvent.getEventDataList().size();
        assert (eventListSize == 3);
    }

    @Test
    public void testAbleToHandleIncompleteEvents() {
        LogEvent logEvent7 = new LogEvent("scsmbstgrd", "STARTED", "1491377495219", "", "");
        logEvents.add(logEvent7);
        ProcessLogEvent processLogEvent = new ProcessLogEvent(logEvents);

        int eventListSize = processLogEvent.getEventDataList().size();
        assert (eventListSize == 3);

    }


}
