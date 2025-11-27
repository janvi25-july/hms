package com.property.service;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import com.property.Repository.AreaRepository;
import com.property.Repository.CityRepository;
import com.property.Repository.PropertyRepository;
import com.property.Repository.PropertyphotoRepository;
import com.property.Repository.RoomsAvailabilityRepository;
import com.property.Repository.RoomsRepository;
import com.property.Repository.StateRepository;
import com.property.dto.APIResponse;
import com.property.dto.PropertyDto;

import com.property.dto.RoomDto;
import com.property.entity.Area;
import com.property.entity.City;
import com.property.entity.Property;
import com.property.entity.PropertyPhoto;
import com.property.entity.Rooms;
import com.property.entity.RoomsAvailability;
import com.property.entity.State;

@Service
public class PropertyService {


	private PropertyRepository propertyRepository;
	private S3Service s3Service;
   private PropertyphotoRepository photoRepository;

	private AreaRepository areaRepository;	
	
	public PropertyService(PropertyRepository propertyRepository, S3Service s3Service,
			PropertyphotoRepository propertyphotoRepository, AreaRepository areaRepository,
			CityRepository cityRepository, StateRepository stateRepository, RoomsRepository roomsRepository,
			RoomsAvailabilityRepository roomsavailabilityRepository) {
		super();
		this.propertyRepository = propertyRepository;
		this.s3Service = s3Service;
		this.photoRepository = propertyphotoRepository;
		this.areaRepository = areaRepository;
		this.cityRepository = cityRepository;
		this.stateRepository = stateRepository;
		this.roomsRepository = roomsRepository;
		this.roomsavailabilityRepository = roomsavailabilityRepository;
	}


	private CityRepository cityRepository;
	
	private StateRepository stateRepository;
	
	private RoomsRepository roomsRepository;
   private RoomsAvailabilityRepository roomsavailabilityRepository;
	

	public PropertyDto addProperty(PropertyDto dto, MultipartFile[] files) {
		
	    Area area = areaRepository.findByName(dto.getArea());
	    City city = cityRepository.findByName(dto.getCity());
	    State state = stateRepository.findByName(dto.getState());

	    Property property = new Property();
	    
	    property.setName(dto.getName());
	    property.setNumberOfBathrooms(dto.getNumberOfBathrooms());
	    property.setNumberOfBeds(dto.getNumberOfBeds());
	    property.setNumberOfRooms(dto.getNumberOfRooms());
	    property.setNumberOfGuestAllowed(dto.getNumberOfGuestAllowed());
	    
	    property.setArea(area);
	    property.setCity(city);
	    property.setState(state);

	    Property savedProperty = propertyRepository.save(property);

	    // Save rooms
	    //for (RoomDto roomsDto : dto.getRooms()) {
	      //  Rooms rooms = new Rooms();
	       // rooms.setProperty(savedProperty);
	       // rooms.setRoomType(roomsDto.getRoomType());
	      //  rooms.setBaseprice(roomsDto.getBasePrice());
	      //  roomRepository.save(rooms);
	  //  }
	    List<String> fileUrls = s3Service.uploadFiles(files);//urls
	    for(String url : fileUrls){
	    System.out.println(url);
	    
	    PropertyPhoto photos =new  PropertyPhoto();
	   photos.setUrl(url);
	    photos.setProperty(savedProperty);
	    photoRepository.save(photos);
	    
	   	}
	    return dto;
	}


	public APIResponse<List<Property>> searchProperty(String city, LocalDate date) {
   List<Property> properties =propertyRepository.searchProperty(city,date);
   
   APIResponse<List<Property>> response= new APIResponse<>();
   
   response.setMessage("Search Result");
   response.setData(properties);
   response.setStatus(200);
     return response;
	}

	public APIResponse<PropertyDto> findPropertyById(long id){
		APIResponse<PropertyDto> response = new APIResponse<>();
		PropertyDto dto  = new PropertyDto();
		Optional<Property> opProp = propertyRepository.findById(id);
		
		if(opProp.isPresent()) {
			Property property = opProp.get();
			dto.setArea(property.getArea().getName());
			dto.setCity(property.getCity().getName());
			dto.setState(property.getState().getName());
			List<Rooms> rooms = property.getRooms();
			List<RoomDto> roomsDto = new ArrayList<>();
			
			for(Rooms room:rooms) {
				RoomDto roomDto = new RoomDto();
				BeanUtils.copyProperties(room, roomDto);
				roomsDto.add(roomDto);
			}
			dto.setRooms(roomsDto);
			BeanUtils.copyProperties(property, dto);
			response.setMessage("Matching Record");
			response.setStatus(200);
			response.setData(dto);
			return response;
		}
	
		return null;
	}
		public List<RoomsAvailability> getTotalRoomsAvailable(long id) {
			return roomsavailabilityRepository.findByRoomId(id);
			
		}
		public Rooms getRoomType(long id){
	  return roomsRepository.findById(id).get();
		
				
		}
	
}	

	


	
	
	
	
	
	

	   