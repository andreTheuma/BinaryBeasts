package com.binarybeasts.sport.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.binarybeasts.sport.models.Booking;
import com.binarybeasts.sport.models.BookingRequest;
import com.binarybeasts.sport.models.BookingResponse;
import com.binarybeasts.sport.services.BookingService;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class BookingControllerTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private BookingService bookingMockService;

    @Test
    public void testCreateValidBooking() throws Exception {
        BookingRequest bookingRequest = new BookingRequest(3L, 5L, 500L, 1000L);

        BookingResponse expectedBookingResponse = new BookingResponse(1L, 3L, 5L, 500L, 1000L);

        String expectedResponseBody = om.writeValueAsString(expectedBookingResponse);

        String endpoint = "/schedule";

        Booking serviceBooking = new Booking(1L, 3L, 5L, 500L, 1000L);

        when(bookingMockService.saveBooking(any(Booking.class))).thenReturn(serviceBooking);

        ResponseEntity<String> responseEntity =
                testRestTemplate.postForEntity(endpoint, bookingRequest, String.class);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        JSONAssert.assertEquals(expectedResponseBody, responseEntity.getBody(), true);
    }


    @Test
    public void testCreateTooShortBooking(){
        BookingRequest bookingRequest = new BookingRequest(2L, 10L, 123123L, 600L);

        String endpoint = "/schedule";

        ResponseEntity<String> responseEntity =
                testRestTemplate.postForEntity(endpoint, bookingRequest, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}
