package com.booking.dto;

import java.time.LocalDate;


public class RoomAvailability {
	 private long id;

	    private LocalDate availableDate;
	    public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

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

		private int availableCount;
	    private double price;
     private RoomDto roomDto;
     public RoomDto getRoomDto() {
	return roomDto;
}

public void setRoomDto(RoomDto roomDto) {
	this.roomDto = roomDto;
}
}
