package com.binarybeasts.sport.services;

import com.binarybeasts.sport.models.Booking;
import com.binarybeasts.sport.models.BookingEntity;
import com.binarybeasts.sport.repositories.BookingRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class BookingServiceTest {

    @MockBean
    BookingRepository bookingMockRepository;

    @Autowired
    BookingService bookingService;

    @Autowired
    ModelMapper modelMapper;

		@Test
		public void testSaveBooking(){
        Booking bookingToBeSaved = new Booking(90L, 21L, 5555L, 11121L);

        Booking expectedSavedBooking = new Booking(1L, 90L, 21L, 5555L, 11121L);

        BookingEntity outputBookingEntity = new BookingEntity(1L, 90L, 21L, 5555L, 11121L);
        when(bookingMockRepository.save(any(BookingEntity.class))).thenReturn(outputBookingEntity);

        Booking savedBooking = bookingService.saveBooking(bookingToBeSaved);

        assertThat(savedBooking).isEqualToComparingFieldByField(expectedSavedBooking);
        verify(bookingMockRepository, times(1)).save(any(BookingEntity.class));
		}
}
