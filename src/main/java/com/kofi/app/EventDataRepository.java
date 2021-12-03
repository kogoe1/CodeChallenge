package com.kofi.app;


import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventDataRepository extends CrudRepository<EventData, String>{
    List<EventData> findByIsAlerted(boolean isAlerted);
    EventData findByEventId(String eventId);
    EventData findByType(String type);
    EventData findByHost(String host);
}


