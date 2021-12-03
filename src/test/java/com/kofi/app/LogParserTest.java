package com.kofi.app;



import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;



@RunWith(JUnit4.class)
public class LogParserTest {
    private String filename;
    private String nonExistentFile;

    @Before
    public void init(){
        filename = "/Users/kofiogoe/kogoe1/dev/code_challenge/src/main/resources/logfile.txt";
        // This File does not exist
        nonExistentFile = "/Users/kofiogoe/kogoe1/dev/code_challenge/src/main/resources/logfile2.txt";
//        String filename1a = "resources/logfile.txt";
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
        assert (logParser.parseJSON().size() == 0 );
    }

    @Test
    public void testSuccessfullyLoadAllJSONObjectsInLogFile() throws IOException {
        LogParser logParser = new LogParser(filename);

        List<LogEvent> logEventList = logParser.parseJSON();
        assertNotNull(logEventList);
//        for (LogEvent logEvent : logEventList){
//            System.out.println("id: " + logEvent.getId()
//                    + "\n state: " + logEvent.getState()
//                    + "\n type: " + logEvent.getType()
//                    + "\n type: " + logEvent.getTimestamp()
//                    + "\n host: " + logEvent.getHost()
//            );
//        }
        assert (logEventList.size() == 6);
    }

}
