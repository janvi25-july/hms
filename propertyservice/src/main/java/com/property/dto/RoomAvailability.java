package com.property.dto;

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

		public RoomDto getRoomdto() {
			return roomdto;
		}

		public void setRoomdto(RoomDto roomdto) {
			this.roomdto = roomdto;
		}

		private int availableCount;
	    private double price;

	    private RoomDto roomdto;
}
