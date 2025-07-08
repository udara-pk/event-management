package com.eventapp.event_management_backend.service;

import com.eventapp.event_management_backend.domain.Event;
import com.eventapp.event_management_backend.domain.Event.Visibility;
import com.eventapp.event_management_backend.dto.EventRequest;
import com.eventapp.event_management_backend.repository.AttendanceRepository;
import com.eventapp.event_management_backend.repository.EventRepository;
import com.eventapp.event_management_backend.service.impl.EventServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EventServiceImplTest {

    private EventRepository eventRepository;
    private AttendanceRepository attendanceRepository;
    private EventService eventService;

    @BeforeEach
    void setUp() {
        eventRepository = mock(EventRepository.class);
        attendanceRepository = mock(AttendanceRepository.class);
        eventService = new EventServiceImpl(eventRepository, attendanceRepository);
    }

    @Test
    void createEvent_shouldReturnSavedEvent() {
        UUID hostId = UUID.randomUUID();
        EventRequest request = new EventRequest();
        request.setTitle("Test Event");
        request.setDescription("Description");
        request.setStartTime(LocalDateTime.now().plusDays(1));
        request.setEndTime(LocalDateTime.now().plusDays(2));
        request.setLocation("Colombo");
        request.setVisibility(Visibility.PUBLIC);

        Event mockEvent = Event.builder()
                .id(UUID.randomUUID())
                .title(request.getTitle())
                .description(request.getDescription())
                .hostId(hostId)
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .location(request.getLocation())
                .visibility(request.getVisibility())
                .archived(false)
                .build();

        when(eventRepository.save(any(Event.class))).thenReturn(mockEvent);
        when(attendanceRepository.findByEventId(mockEvent.getId())).thenReturn(Collections.emptyList());

        var result = eventService.createEvent(hostId, request);
        assertEquals("Test Event", result.getTitle());
        verify(eventRepository, times(1)).save(any(Event.class));
    }
}
