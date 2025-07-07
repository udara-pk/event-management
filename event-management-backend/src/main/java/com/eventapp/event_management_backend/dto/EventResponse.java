package com.eventapp.event_management_backend.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
import com.eventapp.event_management_backend.domain.Event;

@Data
@Builder
public class EventResponse {
    private UUID id;
    private String title;
    private String description;
    private UUID hostId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;
    private Event.Visibility visibility;
    private long attendeeCount;
}
