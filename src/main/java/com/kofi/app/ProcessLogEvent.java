package com.kofi.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class ProcessLogEvent {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessLogEvent.class);

    private List<LogEvent> logEvents;
    private EventDataRepository eventDataRepository;

    public ProcessLogEvent(List<LogEvent> logEvents, EventDataRepository eventDataRepository) {
        this.logEvents = logEvents;
        this.eventDataRepository = eventDataRepository;
    }


    public void processLogs() {
        Set<String> eventIds = new HashSet<>();
        logEvents.parallelStream().forEach(logEvent -> eventIds.add(logEvent.getId()));

        for (String eventId : eventIds) {
            List<LogEvent> logEventPair = logEvents.parallelStream().filter(event -> event.getId().equals(eventId)).collect(Collectors.toList());

            EventData extractedEvenData = extractEventData(logEventPair);
            if (extractedEvenData != null) {
                eventDataRepository.save(extractedEvenData);
                if (extractedEvenData.getIsAlerted()) {
                    LOGGER.debug("Log event with ID " + eventId + " exceeded threshold with duration " + extractedEvenData.getDuration());
                }
            }

        }

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

            LOGGER.debug("Log event pair ID: " + logEventId);

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
            LOGGER.debug("Log event ID " + logEventId + " has only " + logEventPairSize + " occurrence in the logs");
        }

        LOGGER.error("There is no Start and Finish state for log event ID: " + logEventId);
        return null;
    }

}
