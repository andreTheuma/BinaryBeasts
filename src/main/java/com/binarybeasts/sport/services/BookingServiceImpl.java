package com.binarybeasts.sport.services;

import com.binarybeasts.sport.models.Booking;
//import com.binarybeasts.sport.models.BookingResponse;
import com.binarybeasts.sport.models.BookingEntity;
import com.binarybeasts.sport.repositories.BookingRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    //@Autowired
    //private BookingUtils bookingUtils;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Booking saveBooking(Booking booking) {
				modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        BookingEntity bookingEntity = modelMapper.map(booking, BookingEntity.class);
        bookingEntity = bookingRepository.save(bookingEntity);

        Booking savedBooking = modelMapper.map(bookingEntity, Booking.class);

        return savedBooking;
    }

    @Override
    public Booking getBookingById(Long id) {
        //TODO
        return null;
    }

    @Override
    public List<Booking> getAllBookings() {
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			List<BookingEntity> entities = bookingRepository.findAll();
			List<Booking> bookings = new ArrayList<Booking>();
			for (int i = 0;i < entities.size();i++)
				bookings.add(modelMapper.map(entities.get(i), Booking.class));
			return bookings;
    }

    @Override
    public void deleteBookingById(Long id) {
        //TODO
    }
}
