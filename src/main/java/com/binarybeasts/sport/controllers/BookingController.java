package com.binarybeasts.sport.controllers;

import com.binarybeasts.sport.models.Booking;
import com.binarybeasts.sport.models.BookingRequest;
import com.binarybeasts.sport.models.BookingResponse;
import com.binarybeasts.sport.services.BookingService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.ArrayList;

@RestController
public class BookingController {

    @Autowired
    BookingService bookingService;

    @Autowired
    ModelMapper modelMapper;

		//TODO: https://www.baeldung.com/java-modelmapper-lists
		@GetMapping("/allScheds")
    List<BookingResponse> findAll() {
				modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        List<Booking> bookings = bookingService.getAllBookings();
				List<BookingResponse> brList = new ArrayList<BookingResponse>();

				for (int i = 0;i < bookings.size();i++){
					Booking b = bookings.get(i);
					BookingResponse br = modelMapper.map(b, BookingResponse.class);
					brList.add(br);
				}
        return brList;
    }

    @PostMapping("/schedule")
    @ResponseStatus(HttpStatus.CREATED)
    BookingResponse addBooking(@Valid @RequestBody BookingRequest bookingRequest) {
				modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Booking bo = modelMapper.map(bookingRequest, Booking.class);

        Booking savedBooking = bookingService.saveBooking(bo);

        BookingResponse br = modelMapper.map(savedBooking, BookingResponse.class);

        return br;
    }

    BookingResponse findOne(@PathVariable Long id) {
        //TODO
        return null;
    }

    BookingResponse saveOrUpdate(@RequestBody BookingRequest bookingRequest, @PathVariable Long id) {
        //TODO
        return null;
    }

    void deleteBooking(@PathVariable Long id) {
        //TODO
    }
}
