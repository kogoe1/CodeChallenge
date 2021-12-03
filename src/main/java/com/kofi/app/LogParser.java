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
import java.util.Objects;

public class LogParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogParser.class);

    private final String filename;

    public LogParser(String filename) {
        this.filename = filename;
    }

    public List<LogEvent> parseJSON() throws IOException {
        List<LogEvent> logEventList = new ArrayList<>();

        if (Objects.nonNull(filename)) {
            File logfile = new File(filename);

            LOGGER.info("log file " + filename + " has been passed");
            if (logfile.exists() && logfile.canRead()) {
                LOGGER.info("Log file Successfully opened for processing");
                BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
                String lineRead;
                while ((lineRead = bufferedReader.readLine()) != null) {
                    Gson gsonObject = new Gson();
                    LogEvent logEvent = gsonObject.fromJson(lineRead, LogEvent.class);
                    logEventList.add(logEvent);
                }
            } else {
                LOGGER.debug("Passed file is" + filename);
                LOGGER.error("Log file" + filename + " does not exist or cannot be read");
                LOGGER.info("Log file" + filename + " does not exist or cannot be read");
            }
        } else {
            LOGGER.error("No log file was passed");
        }

        return logEventList;
    }


}
