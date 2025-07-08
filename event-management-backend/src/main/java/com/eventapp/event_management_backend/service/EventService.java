package com.eventapp.event_management_backend.service;

import java.util.List;
import java.util.UUID;

import com.eventapp.event_management_backend.dto.EventFilterRequest;
import com.eventapp.event_management_backend.dto.EventRequest;
import com.eventapp.event_management_backend.dto.EventResponse;

public interface EventService {
    EventResponse createEvent(UUID hostId, EventRequest request);
    EventResponse updateEvent(UUID eventId, UUID userId, EventRequest request);
    void deleteEvent(UUID eventId, UUID userId);
    EventResponse getEventDetails(UUID eventId);
    List<EventResponse> listEvents();
    List<EventResponse> listUpcomingEvents(int page, int size);
    List<EventResponse> listUserEvents(UUID userId);
    List<EventResponse> filterEvents(EventFilterRequest filter, int page, int size);
}
