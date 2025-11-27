package com.property.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.property.entity.RoomsAvailability;

public interface RoomsAvailabilityRepository extends JpaRepository<RoomsAvailability, Long> {
	public List<RoomsAvailability> findByRoomId(long id);
}
