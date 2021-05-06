package com.binarybeasts.sport.repositories;

import com.binarybeasts.sport.models.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingEntity, Long>{
}
