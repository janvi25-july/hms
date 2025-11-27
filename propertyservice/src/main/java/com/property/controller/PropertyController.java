package com.property.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.property.dto.APIResponse;
import com.property.dto.PropertyDto;

import com.property.entity.Rooms;
import com.property.entity.RoomsAvailability;
import com.property.service.PropertyService;




@RestController
@RequestMapping("api/v1/property")
public  class PropertyController {
	private PropertyService propertyService;
	
	
	public PropertyController(PropertyService propertyService) {
	
		this.propertyService = propertyService;
	}
	
	
@PostMapping(value="/add-property",
consumes = MediaType.MULTIPART_FORM_DATA_VALUE,  
produces = MediaType.APPLICATION_JSON_VALUE )


public ResponseEntity<APIResponse> addproperty(@RequestParam("property")
String propertyJson, @RequestParam("files") MultipartFile[] files){
	
	 ObjectMapper objectMapper = new ObjectMapper();
	 PropertyDto dto = null;
	   
	    try {
	        dto = objectMapper.readValue(propertyJson, PropertyDto.class);  // Convert JSON string to PropertyDto
	    } catch (JsonProcessingException e) {
	       
	        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Handle bad JSON
	    }

	 PropertyDto property=propertyService.addProperty(dto,files);
	 // Create response object
	    APIResponse<PropertyDto> response = new APIResponse<>();
	    response.setMessage("Property added");
	    response.setStatus(201);
	    response.setData(property);

	    return new ResponseEntity<>(response, HttpStatus.CREATED);
	}



	 @GetMapping("/search-property")
	public APIResponse serachProperty(@RequestParam String name,@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	LocalDate date) {
		 
		 APIResponse response = propertyService.searchProperty(name, date);
		    return response;
		    
		    
	 }
		@GetMapping("/property-id")
	public APIResponse<PropertyDto> getPropertyById(@RequestParam long id) {
	APIResponse<PropertyDto> response =propertyService.findPropertyById(id);
		return response;
		}	

		@GetMapping("/room-available-room-id")
		public APIResponse<List<RoomsAvailability>> getTotalRoomsAvailable(@RequestParam long id){
			List<RoomsAvailability> totalRooms = propertyService.getTotalRoomsAvailable(id);
			
			APIResponse<List<RoomsAvailability>> response = new APIResponse<>();
		    response.setMessage("Total rooms");
		    response.setStatus(200);
		    response.setData(totalRooms);
		    return response;
		}

	
		@GetMapping("/room-id")//by taking room_id giving the detail of room's type
public APIResponse<Rooms>getRoomType(@RequestParam long id){
	Rooms rooms= propertyService.getRoomType(id);
	APIResponse<Rooms> response= new APIResponse<>();
	response.setMessage("Room Type");
	response.setData(rooms);
	response.setStatus(200);
	return response;
}
	
}

