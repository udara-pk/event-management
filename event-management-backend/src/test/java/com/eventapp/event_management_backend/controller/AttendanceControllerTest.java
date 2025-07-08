package com.eventapp.event_management_backend.controller;

import com.eventapp.event_management_backend.domain.AttendanceStatus;
import com.eventapp.event_management_backend.dto.AttendanceRequest;
import com.eventapp.event_management_backend.dto.AttendanceResponse;
import com.eventapp.event_management_backend.service.AttendanceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import java.util.UUID;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@WebMvcTest(AttendanceController.class)
class AttendanceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AttendanceService attendanceService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "user@example.com")
    void respond_shouldReturnCreatedStatus() throws Exception {
        UUID eventId = UUID.randomUUID();
        AttendanceRequest request = new AttendanceRequest();
        request.setEventId(eventId);
        request.setStatus(AttendanceStatus.GOING);

        AttendanceResponse mockResponse = AttendanceResponse.builder()
                .eventId(eventId)
                .userId(UUID.randomUUID())
                .status(AttendanceStatus.GOING)
                .build();

        Mockito.when(attendanceService.respondToEvent(any(), any())).thenReturn(mockResponse);

        mockMvc.perform(post("/api/attendance")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("GOING"));
    }
}
