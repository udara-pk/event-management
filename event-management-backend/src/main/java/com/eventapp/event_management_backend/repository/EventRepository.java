package com.eventapp.event_management_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eventapp.event_management_backend.domain.Event;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
public interface EventRepository extends JpaRepository<Event, UUID> {
    List<Event> findByHostId(UUID hostId);
    List<Event> findByStartTimeAfter(LocalDateTime now);
    List<Event> findByVisibility(Event.Visibility visibility);
}
