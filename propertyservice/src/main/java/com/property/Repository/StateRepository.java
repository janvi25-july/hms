package com.property.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.property.entity.State;

public interface StateRepository extends JpaRepository<State, Long> {
	State findByName(String name);
}
