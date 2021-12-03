package com.kofi.app;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertNotNull;


@RunWith(JUnit4.class)
public class LogParserTest {
    private String filename;
    private String nonExistentFile;

    @Before
    public void init() {
        filename = getClass().getResource("/logfile.txt").getPath();

        // This File does not exist
        nonExistentFile = "logfile2.txt";
    }

    @Test
    public void testFileSuccessfullyLoaded() throws IOException {
        LogParser logParser = new LogParser(filename);

        assertNotNull(logParser);


        List<LogEvent> logEventList = logParser.parseJSON();
        assertNotNull(logEventList);
        assert (logEventList.size() > 0);
    }

    @Test
    public void testCorrectlyHandleNonExistentFile() throws IOException {
        LogParser logParser = new LogParser(nonExistentFile);
        assert (logParser.parseJSON().size() == 0);
    }

    @Test
    public void testSuccessfullyLoadAllJSONObjectsInLogFile() throws IOException {
        LogParser logParser = new LogParser(filename);

        List<LogEvent> logEventList = logParser.parseJSON();
        assertNotNull(logEventList);
        assert (logEventList.size() == 6);
    }

}
