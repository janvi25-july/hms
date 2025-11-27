package com.property.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.property.entity.Rooms;


public interface RoomsRepository extends JpaRepository<Rooms, Long> {

}
