package com.kofi.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.Math.abs;

public class ProcessLogEvent {

    private static final Logger logger = LoggerFactory.getLogger(ProcessLogEvent.class);

    List<LogEvent> logEvents;

    public ProcessLogEvent(List<LogEvent> logEvents) {
        this.logEvents = logEvents;
    }

    public List<EventData> getEventDataList() {
        List<EventData> eventDataList = new ArrayList<>();

        Set<String> eventIds = new HashSet<>();
        for (LogEvent logEvent : logEvents) {
            eventIds.add(logEvent.getId());
        }

        for (String eventId : eventIds) {
            List<LogEvent> logEventPair = logEvents.stream().filter(event -> event.getId().equals(eventId)).collect(Collectors.toList());

            EventData extractedEvenData = extractEventData(logEventPair);
            if (extractedEvenData != null) {
                eventDataList.add(extractedEvenData);
                if (extractedEvenData.getIsAlerted()) { logger.debug("Log event with ID " + eventId + " exceeded threshold with duration " + extractedEvenData.getDuration());}
            }

        }
        return eventDataList;
    }

    private EventData extractEventData(List<LogEvent> logEventPair) {
        long duration = 0;
        long startTime = 0;
        long endTime = 0;

        String logEventId = logEventPair.get(0).getId();
        String logEventType = logEventPair.get(0).getType();
        String logEventHost = logEventPair.get(0).getHost();

        int logEventPairSize = logEventPair.size();
        if (logEventPairSize == 2) {

            logger.debug("Log event pair Id: " + logEventId);

//            long durattion = logEventPair.stream().reduce(0, (a, b) -> abs(a.getTimestamp() - b.getTimestamp())).getTimestamp()

            for (LogEvent logEvent : logEventPair) {
                if (logEvent.getState().equals("STARTED")) {
                    startTime = logEvent.getTimestamp();
                } else { // logEvent is Finished
                    endTime = logEvent.getTimestamp();
                }
                duration = endTime - startTime;
            }
            return new EventData(logEventId, duration, logEventType, logEventHost);
        } else {
            logger.debug("Log event Id " + logEventId + " has only " + logEventPairSize + " occurrences in the logs");
        }

        logger.error("There is no Start and Finish state for log event ID: " + logEventId);
        return null;
    }

//    TODO Persist EventData into DB

//    @Bean
//    public CommandLineRunner demo(EventDataRepository repository, EventData eventData) {
//        return (args) -> {
//            repository.save(eventData);
//
//            EventData eventData = repository.findByEventId(eventId);
//
//            logger.info("Event ID is: " + eventData.getEventId());
//            logger.info("Is Alerted: " + eventData.getIsAlerted());
//        };

//    }

}
