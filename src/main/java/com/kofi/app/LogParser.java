package com.kofi.app;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogParser {
    private static final Logger logger = LoggerFactory.getLogger(LogParser.class);

    private final String filename;

    public LogParser(String filename) {
        this.filename = filename;
    }

    public List<LogEvent> parseJSON() throws IOException {
        List<LogEvent> logEventList = new ArrayList<>();
        File logfile = new File(filename);
        logger.info("log file " + filename + " has been passed");
        if (logfile.exists() && logfile.canRead()){
            logger.info("Log file Successfully opened for processing");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            String lineRead;
            while ((lineRead = bufferedReader.readLine()) != null){
                Gson gsonObject = new Gson();
                LogEvent logEvent = gsonObject.fromJson(lineRead, LogEvent.class);
                logEventList.add(logEvent);
            }
        } else {
            logger.debug("Passed file is" + filename);
            logger.error("Log file" + filename + " does not exist or cannot be read");
            logger.info("Log file" + filename + " does not exist or cannot be read");
        }
        return logEventList;
    }


}
