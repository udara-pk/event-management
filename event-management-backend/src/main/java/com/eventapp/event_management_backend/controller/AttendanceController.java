package com.eventapp.event_management_backend.controller;

import com.eventapp.event_management_backend.dto.AttendanceRequest;
import com.eventapp.event_management_backend.dto.AttendanceResponse;
import com.eventapp.event_management_backend.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping
    public ResponseEntity<AttendanceResponse> respond(@RequestBody AttendanceRequest request,
                                                      Authentication auth) {
        UUID userId = UUID.nameUUIDFromBytes(auth.getName().getBytes());
        return ResponseEntity.ok(attendanceService.respondToEvent(userId, request));
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<AttendanceResponse>> getAttendees(@PathVariable UUID eventId) {
        return ResponseEntity.ok(attendanceService.getEventAttendees(eventId));
    }

    @GetMapping("/user")
    public ResponseEntity<List<AttendanceResponse>> getUserAttendances(Authentication auth) {
        UUID userId = UUID.nameUUIDFromBytes(auth.getName().getBytes());
        return ResponseEntity.ok(attendanceService.getUserAttendances(userId));
    }  
}
