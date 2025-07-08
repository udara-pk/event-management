package com.eventapp.event_management_backend.dto;

import com.eventapp.event_management_backend.domain.AttendanceStatus;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AttendanceResponse {
    private UUID eventId;
    private UUID userId;
    private AttendanceStatus status;
}
