package com.booking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.entity.BookingDate;

public interface BookingDateRepository extends JpaRepository<BookingDate, Long> {

}
