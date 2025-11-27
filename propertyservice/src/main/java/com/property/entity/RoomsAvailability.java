package com.property.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="roomsAvailability")
public class RoomsAvailability {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;


    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private LocalDate availableDate;
    
	

	public LocalDate getAvailableDate() {
		return availableDate;
	}

	public void setAvailableDate(LocalDate availableDate) {
		this.availableDate = availableDate;
	}

	public int getAvailableCount() {
		return availableCount;
	}

	public void setAvailableCount(int availableCount) {
		this.availableCount = availableCount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Rooms getRoom() {
		return room;
	}

	public void setRoom(Rooms room) {
		this.room = room;
	}

	private int availableCount;
    private double price;
   
    @ManyToOne
    @JoinColumn(name = "rooms_id")
    private Rooms room;


}
