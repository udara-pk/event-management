package com.eventapp.event_management_backend.service;

import com.eventapp.event_management_backend.dto.AttendanceRequest;
import com.eventapp.event_management_backend.dto.AttendanceResponse;

import java.util.List;
import java.util.UUID;
public interface AttendanceService {
    AttendanceResponse respondToEvent(UUID userId, AttendanceRequest request);
    List<AttendanceResponse> getEventAttendees(UUID eventId);
    List<AttendanceResponse> getUserAttendances(UUID userId);
}
