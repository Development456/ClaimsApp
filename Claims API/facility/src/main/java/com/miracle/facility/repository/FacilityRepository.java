package com.miracle.facility.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.miracle.facility.entity.Facility;


public interface FacilityRepository extends MongoRepository<Facility, String> {
	
	public List<Facility> findAll();
	
	@Query("{'facilityId' : ?0}")
	public Facility findByFacilityId(String facilityId);

	public void deleteByFacilityId(String facilityId);
	
	@Query("{'city': ?0}")
	public List<Facility> getFacilityByCity(String facilityCity);
	
	@Query("{'facility_name': ?0}")
	public List<Facility> getFacilityByName(String facilityName);
	
	@Query("{'postal_code': ?0}")
	public List<Facility> getFacilityByPostalCode(String postalCode);
	
	@Query("{'state': ?0}")
	public List<Facility> getFacilityByState(String facilityState);
	
	@Query("{'country': ?0}")
	public List<Facility> getFacilityByCountry(String facilityCountry);
	
	@Query("{'phone': ?0}")
	public List<Facility> getFacilityByPhone(String facilityPhone);
	
	@Query("{'facility_manager': ?0}")
	public List<Facility> getFacilityByManager(String facilityManager);
	
	@Query("{'category': ?0}")
	public List<Facility> getFacilityByCategory(String facilityCategory);
	
	
}
