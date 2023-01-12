package com.miracle.facility.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.miracle.facility.entity.Facility;
import com.miracle.facility.repository.FacilityRepository;

@Service
public class FacilityServiceImpl implements FacilityService {

	@Autowired
	FacilityRepository facilityRepository;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	MongoOperations mongoOperations;
	
	//get facility filter
	@Override
	public ResponseEntity<List<Facility>> getAllFacilityFilter(Facility facility, int page, int size, String sort){
		
		Pageable pageable = PageRequest.of(page, size);
		
		Query query = new Query();//.with(pageable);
		query.with(pageable);
		
		List<Criteria> criteria = new ArrayList<>();
		
		if(facility.getFacilityId() != null) {
			criteria.add(Criteria.where("facility_id").is(facility.getFacilityId()));
		}
		if(facility.getAddressLine1() != null) {
			criteria.add(Criteria.where("address_line_1").is(facility.getAddressLine1()));
		}
		if(facility.getAddressLine2() != null) {
			criteria.add(Criteria.where("address_line_2").is(facility.getAddressLine2()));
		}
		if(facility.getAddressLine3() != null) {
			criteria.add(Criteria.where("address_line_3").is(facility.getAddressLine3()));
		}
		if(facility.getCity() != null) {
			criteria.add(Criteria.where("city").is(facility.getCity()));
		}
		if(facility.getFacilityName() != null) {
			criteria.add(Criteria.where("facility_name").is(facility.getFacilityName()));
		}
		if(facility.getPostalCode() != null) {
			criteria.add(Criteria.where("postal_code").is(facility.getPostalCode()));
		}
		if(facility.getState() != null) {
			criteria.add(Criteria.where("state").is(facility.getState()));
		}
		if(facility.getCountry() != null) {
			criteria.add(Criteria.where("country").is(facility.getCountry()));
		}
		if(facility.getPhone() != null) {
			criteria.add(Criteria.where("phone").is(facility.getPhone()));
		}
		if(facility.getFacilityManager() != null) {
			criteria.add(Criteria.where("facility_manager").is(facility.getFacilityManager()));
		}
		if(facility.getCategory() != null) {
			criteria.add(Criteria.where("category").is(facility.getCategory()));
		}
		
		query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));//this is working fine
		
		
		
		List<Facility> filteredVals = mongoOperations.find(query, Facility.class);
		
		return new ResponseEntity<List<Facility>>(filteredVals, new HttpHeaders(), HttpStatus.OK);
		
	}


	// get all facility
	@Override
	public ResponseEntity<List<Facility>> getAll() {
		List<Facility> facility = facilityRepository.findAll();
		return new ResponseEntity<List<Facility>>(facility, new HttpHeaders(), HttpStatus.OK);
	}
	
	//get by facilityId
	@Override
	public List<Facility> getById(String facilityId){
		List<Facility> facility = new ArrayList<>();
		facility.add(facilityRepository.findByFacilityId(facilityId));
		return facility;
	}
	
	
	// post a a new Facility
	@Override
	public Facility addNewFacility(Facility facility) throws Exception {
		Facility facilities = facilityRepository.findByFacilityId(facility.getFacilityId());
		if (facilities == null) {
			Facility newFacility = facilityRepository.save(facility);
			return newFacility;
		}
		throw new Exception("duplicate facility id");
	}

	@Override
	public Facility updateFacility(String facilityId, Facility facility) {
		try {
			Facility facilities = facilityRepository.findByFacilityId(facilityId);

			System.out.println("the id is" + facilities.getAddressLine1());

			facilities.setAddressLine1(facility.getAddressLine1());
			facilities.setAddressLine2(facility.getAddressLine2());
			facilities.setAddressLine3(facility.getAddressLine3());
			facilities.setCity(facility.getCity());
			facilities.setFacilityName(facility.getFacilityName());
			facilities.setPostalCode(facility.getPostalCode());
			facilities.setState(facility.getState());
			facilities.setCountry(facility.getCountry());
			facilities.setPhone(facility.getPhone());
			facilities.setFacilityManager(facility.getFacilityManager());
			facilities.setCategory(facility.getCategory());
			facilityRepository.save(facilities);

			return facilities;
		} catch (Exception e) {
			return facility ;
		}
	}

	@Override
	public void deleteFacility(String FacilityId) {
		facilityRepository.deleteByFacilityId(FacilityId);
	}
	
	//get facility by city
	@Override
	public List<Facility> getFacilityByCity(String facilityCity) {
		
		return facilityRepository.getFacilityByCity(facilityCity);
	}
	
	//get facility by name
	@Override
	public List<Facility> getFacilityByName(String facilityName){
		return facilityRepository.getFacilityByName(facilityName);
	}
	
	//get facility by postal code
	@Override
	public List<Facility> getFacilityByCode(String postalCode) {
		return facilityRepository.getFacilityByPostalCode(postalCode);
	}
	
	//get facility by state
	@Override
	public List<Facility> getFacilityByState(String facilityState){
		return facilityRepository.getFacilityByState(facilityState);
	}
	
	//get facility by country
	@Override
	public List<Facility> getFacilityByCountry(String facilityCountry){
		return facilityRepository.getFacilityByCountry(facilityCountry);
	}
	
	//get facility by phone
	@Override
	public List<Facility> getFacilityByPhone(String facilityPhone) {
		return facilityRepository.getFacilityByPhone(facilityPhone);
	}
	
	//get the list of facility by manager
	@Override
	public List<Facility> getFacilityByFacilityManager(String facilityManager){
		return facilityRepository.getFacilityByManager(facilityManager);
	}
	
	//get list by category
	@Override
	public List<Facility> getFacilityByCategory(String facilityCategory){
		return facilityRepository.getFacilityByCategory(facilityCategory);
	}
}
