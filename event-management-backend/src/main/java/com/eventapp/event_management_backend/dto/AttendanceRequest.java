package com.eventapp.event_management_backend.dto;

import com.eventapp.event_management_backend.domain.AttendanceStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class AttendanceRequest {
    private UUID eventId;
    private AttendanceStatus status;
}
