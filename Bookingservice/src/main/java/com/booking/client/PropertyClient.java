package com.booking.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;

import com.booking.dto.APIResponse;
import com.booking.dto.PropertyDto;
import com.booking.dto.RoomAvailability;
import com.booking.dto.RoomDto;

@FeignClient(name="PROPERTYDB")
public interface PropertyClient {

@GetMapping("/api/v1/property/property-id")
public APIResponse<PropertyDto> getPropertyById(@RequestParam long id);

@GetMapping("/api/v1/property/room-available-room-id")
public APIResponse<List<RoomAvailability>> getTotalRoomsAvailable(@RequestParam long id); 

@GetMapping("/api/v1/property/room-id")
public APIResponse<RoomDto> getRoomType(@RequestParam long id);




}