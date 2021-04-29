package com.binarybeasts.sport.services;

import com.binarybeasts.sport.models.Booking;

import java.util.List;
//import optional?

public interface BookingService{
	Booking saveBooking(Booking b);
	List<Booking> getAllBookings();
	Booking getBookingById(Long id);
	void deleteBookingById(Long id);
}
