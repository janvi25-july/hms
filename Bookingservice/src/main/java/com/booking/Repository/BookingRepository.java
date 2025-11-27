package com.booking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.entity.Bookings;

public interface BookingRepository extends JpaRepository<Bookings, Long> {

}
