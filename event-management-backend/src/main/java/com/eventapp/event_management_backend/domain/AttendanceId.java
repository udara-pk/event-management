package com.eventapp.event_management_backend.domain;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceId implements Serializable{
    private UUID eventId;
    private UUID userId;
}
