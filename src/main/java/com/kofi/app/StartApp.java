package com.kofi.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import java.util.List;

@SpringBootApplication
@ImportResource(value="classpath:/hsqldb_cfg.xml")
public class StartApp implements CommandLineRunner {

    private String filename;
    private static final Logger LOGGER = LoggerFactory.getLogger(StartApp.class);

    public static void main(String[] args) {
        SpringApplication.run(StartApp.class, args);
    }

    public void run(String... args){
        if (args.length > 0){
            filename = args[0];
        }else {
            LOGGER.info("No args were passed");
        }
    }

    @Bean
    public CommandLineRunner logFileProcessor(EventDataRepository repository) {

        return (args) -> {

            LogParser logParser = new LogParser(filename);
            List<LogEvent> logEvents = logParser.parseJSON();

            ProcessLogEvent logEventProcessor = new ProcessLogEvent(logEvents, repository);
            logEventProcessor.processLogs();

            LOGGER.info("Log file " + filename + " is successfully processed");
        };
    }

}
