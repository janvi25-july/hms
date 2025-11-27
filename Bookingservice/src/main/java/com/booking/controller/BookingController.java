package com.booking.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.Repository.BookingDateRepository;
import com.booking.Repository.BookingRepository;
import com.booking.client.PropertyClient;
import com.booking.dto.APIResponse;
import com.booking.dto.BookingDto;
import com.booking.dto.PropertyDto;
import com.booking.dto.RoomAvailability;
import com.booking.dto.RoomDto;
import com.booking.entity.BookingDate;
import com.booking.entity.Bookings;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {
	@Autowired
	private PropertyClient propertyClient;
	@Autowired
	private BookingRepository bookingrepository;
	@Autowired
	private BookingDateRepository bookingDaterepository;


	@PostMapping("/add-to-cart")
	public APIResponse<List<String>> cart(@RequestBody BookingDto bookingDto) {
		// take booking_id and we will return the booking details in a list 
		
		APIResponse<List<String>> apiResponse = new APIResponse<>();
		
		List<String> messages = new ArrayList<>();
		
		APIResponse<PropertyDto> response = propertyClient.getPropertyById(bookingDto.getPropertyId());

		APIResponse<RoomDto> roomType = propertyClient.getRoomType(bookingDto.getRoomId());
		

		APIResponse<List<RoomAvailability>> totalRoomsAvailable = propertyClient.getTotalRoomsAvailable(bookingDto.getRoomAvailabilityId());
		
		List<RoomAvailability> availableRooms = totalRoomsAvailable.getData();
		

		// Logic to check available rooms based on date and count
		for (LocalDate date : bookingDto.getDate()) {
		    boolean isAvailable = false;

		    for (RoomAvailability ra : availableRooms) {
		        if (ra.getAvailableDate().equals(date) && ra.getAvailableCount() > 0) {
		            isAvailable = true;
		            break; // no need to check further once availability is found
		        }
		    }

		    System.out.println("Date " + date + " available: " + isAvailable);

		    if (!isAvailable) {
		        messages.add("Room not available on: " + date);
		        apiResponse.setMessage("Sold Out");
		        apiResponse.setStatus(500);
		        apiResponse.setData(messages);
		        return apiResponse;
		    }
		}
Bookings booking=new Bookings();
booking.setName(bookingDto.getName());
booking.setEmail(bookingDto.getEmail());
booking.setMobile(bookingDto.getMobile());
booking.setPropertyName(response.getData().getName());
booking.setTotalPrice(roomType.getData().getBasePrice()*bookingDto.getTotalNigths());
booking.setStatus("pending..");
Bookings savedBooking = bookingrepository.save(booking);

for(LocalDate date: bookingDto.getDate()) {
	BookingDate  bookingDate = new BookingDate();
	bookingDate.setDate(date);
	bookingDate.setBookings(savedBooking);
	
	bookingDaterepository.save(bookingDate);
}
return null;
}
}
