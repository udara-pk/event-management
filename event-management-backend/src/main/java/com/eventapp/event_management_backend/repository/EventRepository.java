package com.eventapp.event_management_backend.repository;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eventapp.event_management_backend.domain.Event;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
public interface EventRepository extends JpaRepository<Event, UUID> {
    List<Event> findByHostId(UUID hostId);
    List<Event> findByStartTimeAfter(LocalDateTime now);
    List<Event> findByVisibility(Event.Visibility visibility);
    List<Event> findByHostIdAndArchivedFalse(UUID hostId);
    List<Event> findByStartTimeAfterAndArchivedFalse(LocalDateTime now, PageRequest pageable);
    @Query("SELECT e FROM Event e WHERE e.archived = false")
    List<Event> findAllActive();
}
