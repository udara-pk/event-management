package com.eventapp.event_management_backend.dto;

import com.eventapp.event_management_backend.domain.Event;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class EventFilterRequest {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startAfter;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endBefore;

    private String location;
    private Event.Visibility visibility;
}
