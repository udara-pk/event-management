package com.eventapp.event_management_backend.controller;

import com.eventapp.event_management_backend.dto.EventFilterRequest;
import com.eventapp.event_management_backend.dto.EventRequest;
import com.eventapp.event_management_backend.dto.EventResponse;
import com.eventapp.event_management_backend.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping
    public ResponseEntity<EventResponse> createEvent(@RequestBody EventRequest request, Authentication auth) {
        UUID hostId = UUID.nameUUIDFromBytes(auth.getName().getBytes());
        return ResponseEntity.ok(eventService.createEvent(hostId, request));
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<EventResponse> updateEvent(@PathVariable UUID eventId,
                                                     @RequestBody EventRequest request,
                                                     Authentication auth) {
        UUID userId = UUID.nameUUIDFromBytes(auth.getName().getBytes());
        return ResponseEntity.ok(eventService.updateEvent(eventId, userId, request));
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable UUID eventId, Authentication auth) {
        UUID userId = UUID.nameUUIDFromBytes(auth.getName().getBytes());
        eventService.deleteEvent(eventId, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventResponse> getEventDetails(@PathVariable UUID eventId) {
        return ResponseEntity.ok(eventService.getEventDetails(eventId));
    }

    @GetMapping
    public ResponseEntity<List<EventResponse>> listEvents() {
        return ResponseEntity.ok(eventService.listEvents());
    }

    @GetMapping("/upcoming")
    public ResponseEntity<List<EventResponse>> listUpcomingEvents(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(eventService.listUpcomingEvents(page, size));
    }

    @GetMapping("/user")
    public ResponseEntity<List<EventResponse>> listUserEvents(Authentication auth) {
        UUID userId = UUID.nameUUIDFromBytes(auth.getName().getBytes());
        return ResponseEntity.ok(eventService.listUserEvents(userId));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<EventResponse>> filterEvents(
            @ModelAttribute EventFilterRequest filter,
            @RequestParam int page,
            @RequestParam int size) {
        return ResponseEntity.ok(eventService.filterEvents(filter, page, size));
    }
}
