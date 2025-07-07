package com.eventapp.event_management_backend.dto;

import lombok.Data;

import java.time.LocalDateTime;
import com.eventapp.event_management_backend.domain.Event;

@Data
public class EventRequest {
    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;
    private Event.Visibility visibility;
}
