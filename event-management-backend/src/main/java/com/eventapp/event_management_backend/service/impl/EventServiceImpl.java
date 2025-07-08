package com.eventapp.event_management_backend.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.eventapp.event_management_backend.domain.Event;
import com.eventapp.event_management_backend.dto.EventFilterRequest;
import com.eventapp.event_management_backend.dto.EventRequest;
import com.eventapp.event_management_backend.dto.EventResponse;
import com.eventapp.event_management_backend.repository.AttendanceRepository;
import com.eventapp.event_management_backend.repository.EventRepository;
import com.eventapp.event_management_backend.service.EventService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final AttendanceRepository attendanceRepository;

    @Override
    public EventResponse createEvent(UUID hostId, EventRequest request) {
        Event event = Event.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .hostId(hostId)
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .location(request.getLocation())
                .visibility(request.getVisibility())
                .build();
        Event saved = eventRepository.save(event);
        return toResponse(saved);
    }

    @Override
    public EventResponse updateEvent(UUID eventId, UUID userId, EventRequest request) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));
        if (!event.getHostId().equals(userId)) {
            throw new SecurityException("Unauthorized to update event");
        }
        event.setTitle(request.getTitle());
        event.setDescription(request.getDescription());
        event.setStartTime(request.getStartTime());
        event.setEndTime(request.getEndTime());
        event.setLocation(request.getLocation());
        event.setVisibility(request.getVisibility());
        return toResponse(eventRepository.save(event));
    }

    // @Override
    // public void deleteEvent(UUID eventId, UUID userId) {
    //     Event event = eventRepository.findById(eventId)
    //             .orElseThrow(() -> new IllegalArgumentException("Event not found"));
    //     if (!event.getHostId().equals(userId)) {
    //         throw new SecurityException("Unauthorized to delete event");
    //     }
    //     eventRepository.delete(event);
    // }

    @Override
    public void deleteEvent(UUID eventId, UUID userId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));
        if (!event.getHostId().equals(userId)) {
            throw new SecurityException("Unauthorized to delete event");
        }
        event.setArchived(true);
        eventRepository.save(event);
    }

    @Override
    public List<EventResponse> listEvents() {
        return eventRepository.findAllActive().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EventResponse getEventDetails(UUID eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));
        return toResponse(event);
    }

    // @Override
    // public List<EventResponse> listEvents() {
    //     return eventRepository.findAll().stream()
    //             .map(this::toResponse)
    //             .collect(Collectors.toList());
    // }

    @Override
    public List<EventResponse> listUpcomingEvents(int page, int size) {
        return eventRepository.findByStartTimeAfter(LocalDateTime.now()).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventResponse> listUserEvents(UUID userId) {
        return eventRepository.findByHostId(userId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventResponse> filterEvents(EventFilterRequest filter, int page, int size) {
        List<Event> events = eventRepository.findAll().stream()
                .filter(e -> filter.getStartAfter() == null || !e.getStartTime().isBefore(filter.getStartAfter()))
                .filter(e -> filter.getEndBefore() == null || !e.getEndTime().isAfter(filter.getEndBefore()))
                .filter(e -> filter.getLocation() == null || e.getLocation().equalsIgnoreCase(filter.getLocation()))
                .filter(e -> filter.getVisibility() == null || e.getVisibility() == filter.getVisibility())
                .skip((long) page * size)
                .limit(size)
                .toList();

        return events.stream().map(this::toResponse).collect(Collectors.toList());
    }

    private EventResponse toResponse(Event event) {
        long attendeeCount = attendanceRepository.findByEventId(event.getId()).size();
        return EventResponse.builder()
                .id(event.getId())
                .title(event.getTitle())
                .description(event.getDescription())
                .hostId(event.getHostId())
                .startTime(event.getStartTime())
                .endTime(event.getEndTime())
                .location(event.getLocation())
                .visibility(event.getVisibility())
                .attendeeCount(attendeeCount)
                .build();
    }
}
