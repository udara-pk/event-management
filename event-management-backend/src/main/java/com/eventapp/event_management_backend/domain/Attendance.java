package com.eventapp.event_management_backend.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "attendance")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(AttendanceId.class)
public class Attendance {
    @Id
    private UUID eventId;

    @Id
    private UUID userId;

    @Enumerated(EnumType.STRING)
    private AttendanceStatus status;

    private Instant respondedAt;
}
