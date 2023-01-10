package com.miracle.facility.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.miracle.facility.entity.Facility;

public interface FacilityService {
	
		//get by filter
		public ResponseEntity<List<Facility>> getAllFacilityFilter(Facility facility, int page, int size, String sort);
		
		// get all facility
		public ResponseEntity<List<Facility>> getAll(); 
		
		//get by facilityId
		public List<Facility> getById(String facilityId);
		
		
		// post a a new Facility
		public Facility addNewFacility(Facility facility) throws Exception; 
		
		public Facility updateFacility(String facilityId, Facility facility);

		public void deleteFacility(String FacilityId);
		
		//get facility by city
		public List<Facility> getFacilityByCity(String city);
		
		//get facility by name
		public List<Facility> getFacilityByName(String facilityName);
		
		//get facility by postal code
		public List<Facility> getFacilityByCode(String code);
		
		//get facility by state
		public List<Facility> getFacilityByState(String state);
		
		//get facility by country
		public List<Facility> getFacilityByCountry(String country);
		
		//get facility by phone
		public List<Facility> getFacilityByPhone(String phone);
		
		//get the list of facility by manager
		public List<Facility> getFacilityByFacilityManager(String manager);
		
		//get list by category
		public List<Facility> getFacilityByCategory(String category);
}
