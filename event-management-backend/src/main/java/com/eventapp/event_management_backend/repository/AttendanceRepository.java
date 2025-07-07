package com.eventapp.event_management_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eventapp.event_management_backend.domain.Attendance;
import com.eventapp.event_management_backend.domain.AttendanceId;

import java.util.List;
import java.util.UUID;
public interface AttendanceRepository extends JpaRepository<Attendance, AttendanceId> {
    List<Attendance> findByUserId(UUID userId);
    List<Attendance> findByEventId(UUID eventId);
}
