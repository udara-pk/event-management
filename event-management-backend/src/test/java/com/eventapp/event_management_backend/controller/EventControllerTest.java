package com.eventapp.event_management_backend.controller;

import com.eventapp.event_management_backend.domain.Event;
import com.eventapp.event_management_backend.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EventRepository eventRepository;

    @Test
    void listEvents_shouldReturn200() throws Exception {
        // Seed test data
        Event event = Event.builder()
                .id(UUID.randomUUID())
                .title("Integration Test Event")
                .description("Integration Desc")
                .hostId(UUID.randomUUID())
                .startTime(LocalDateTime.now().plusDays(1))
                .endTime(LocalDateTime.now().plusDays(2))
                .location("Kandy")
                .visibility(Event.Visibility.PUBLIC)
                .archived(false)
                .build();
        eventRepository.save(event);

        mockMvc.perform(get("/api/events"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Integration Test Event"));
    }
}

