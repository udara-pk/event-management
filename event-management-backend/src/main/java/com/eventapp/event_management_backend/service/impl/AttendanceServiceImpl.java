package com.eventapp.event_management_backend.service.impl;

import com.eventapp.event_management_backend.domain.Attendance;
import com.eventapp.event_management_backend.dto.AttendanceRequest;
import com.eventapp.event_management_backend.dto.AttendanceResponse;
import com.eventapp.event_management_backend.repository.AttendanceRepository;
import com.eventapp.event_management_backend.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;

    @Override
    public AttendanceResponse respondToEvent(UUID userId, AttendanceRequest request) {
        Attendance attendance = Attendance.builder()
                .eventId(request.getEventId())
                .userId(userId)
                .status(request.getStatus())
                .respondedAt(Instant.now())
                .build();
        attendanceRepository.save(attendance);
        return toResponse(attendance);
    }

    @Override
    public List<AttendanceResponse> getEventAttendees(UUID eventId) {
        return attendanceRepository.findByEventId(eventId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AttendanceResponse> getUserAttendances(UUID userId) {
        return attendanceRepository.findByUserId(userId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private AttendanceResponse toResponse(Attendance a) {
        return AttendanceResponse.builder()
                .eventId(a.getEventId())
                .userId(a.getUserId())
                .status(a.getStatus())
                .build();
    }
}
