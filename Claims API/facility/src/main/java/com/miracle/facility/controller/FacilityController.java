package com.miracle.facility.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.miracle.facility.entity.Facility;
import com.miracle.facility.exception.ErrorDetails;
import com.miracle.facility.service.FacilityServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
//import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins="*")
public class FacilityController {

	@Autowired
	private FacilityServiceImpl facilityServiceImpl;
	
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "health of facilities service", notes = "JSON Supported", response = Facility.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "success", response = Facility.class),
			@ApiResponse(code = 400, message = "bad-request", response = ErrorDetails.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorDetails.class),
			@ApiResponse(code = 403, message = "Facility service requires authentication - please check username and password", response = ErrorDetails.class),
			@ApiResponse(code = 404, message = "Data not found", response = ErrorDetails.class),
			@ApiResponse(code = 405, message = "Method not allowed", response = ErrorDetails.class),
			@ApiResponse(code = 500, message = "Internal server error", response = ErrorDetails.class) })
	@GetMapping("/facility/health")
	public ResponseEntity<String> health() {
		return new ResponseEntity<String>("The health is up", new HttpHeaders(), HttpStatus.OK);
	}
	
	//get all 
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Returns All Facilities", notes = "JSON Supported", response = Facility.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "success", response = Facility.class),
			@ApiResponse(code = 400, message = "bad-request", response = ErrorDetails.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorDetails.class),
			@ApiResponse(code = 403, message = "Facility service requires authentication - please check username and password", response = ErrorDetails.class),
			@ApiResponse(code = 404, message = "Data not found", response = ErrorDetails.class),
			@ApiResponse(code = 405, message = "Method not allowed", response = ErrorDetails.class),
			@ApiResponse(code = 500, message = "Internal server error", response = ErrorDetails.class) })
	@GetMapping("/facility")
	public ResponseEntity<List<Facility>> getAll() {
		return facilityServiceImpl.getAll();
		//return new ResponseEntity<ResponseEntity<List<Facility>>>(facility, new HttpHeaders(), HttpStatus.OK);
	}
	//get by facility id
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Returns Facility Details By Facility Id", notes = "JSON Supported", response = Facility.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "success", response = Facility.class),
			@ApiResponse(code = 400, message = "bad-request", response = ErrorDetails.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorDetails.class),
			@ApiResponse(code = 403, message = "Facility service requires authentication - please check username and password", response = ErrorDetails.class),
			@ApiResponse(code = 404, message = "Data not found", response = ErrorDetails.class),
			@ApiResponse(code = 405, message = "Method not allowed", response = ErrorDetails.class),
			@ApiResponse(code = 500, message = "Internal server error", response = ErrorDetails.class) })
	@GetMapping("/facility/the-facility/{facilityId}")
	public ResponseEntity<List<Facility>> getFacilityByFacilityId(
			@ApiParam(value = "Facility Id", required=true) @PathVariable("facilityId") String facilityId) {
		List<Facility> facility = facilityServiceImpl.getById(facilityId);
		return new ResponseEntity<List<Facility>> (facility, new HttpHeaders(), HttpStatus.OK);
	}
	
	//post a facility
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Add Facility", notes = "JSON Supported", response = Facility.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "success", response = Facility.class),
			@ApiResponse(code = 400, message = "bad-request", response = ErrorDetails.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorDetails.class),
			@ApiResponse(code = 403, message = "Facility service requires authentication - please check username and password", response = ErrorDetails.class),
			@ApiResponse(code = 404, message = "Data not found", response = ErrorDetails.class),
			@ApiResponse(code = 405, message = "Method not allowed", response = ErrorDetails.class),
			@ApiResponse(code = 500, message = "Internal server error", response = ErrorDetails.class) })
	@PostMapping("/facility")
	public ResponseEntity<Facility> addNewFacility(@RequestBody Facility facility) throws Exception {
		Facility facilities = facilityServiceImpl.addNewFacility(facility);
		return new ResponseEntity<Facility>(facilities, new HttpHeaders(), HttpStatus.OK);
	}
	
	//put facility
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Update Facility", notes = "JSON Supported", response = Facility.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "success", response = Facility.class),
			@ApiResponse(code = 400, message = "bad-request", response = ErrorDetails.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorDetails.class),
			@ApiResponse(code = 403, message = "Facility service requires authentication - please check username and password", response = ErrorDetails.class),
			@ApiResponse(code = 404, message = "Data not found", response = ErrorDetails.class),
			@ApiResponse(code = 405, message = "Method not allowed", response = ErrorDetails.class),
			@ApiResponse(code = 500, message = "Internal server error", response = ErrorDetails.class) })
	@PutMapping("/facility/{facilityId}")
	public ResponseEntity<Facility> updateFacility(@PathVariable String facilityId, @RequestBody Facility facility) {
		Facility facilities = facilityServiceImpl.updateFacility(facilityId, facility);
		return new ResponseEntity<Facility>(facilities, new HttpHeaders(), HttpStatus.OK);
	}
	
	//delete the facility
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Delete Facility", notes = "JSON Supported", response = Facility.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "success", response = Facility.class),
			@ApiResponse(code = 400, message = "bad-request", response = ErrorDetails.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorDetails.class),
			@ApiResponse(code = 403, message = "Facility service requires authentication - please check username and password", response = ErrorDetails.class),
			@ApiResponse(code = 404, message = "Data not found", response = ErrorDetails.class),
			@ApiResponse(code = 405, message = "Method not allowed", response = ErrorDetails.class),
			@ApiResponse(code = 500, message = "Internal server error", response = ErrorDetails.class) })
	@DeleteMapping("/facility/{facilityId}")
	public void deleteFacility(@PathVariable String facilityId) {
		facilityServiceImpl.deleteFacility(facilityId);
	}
	
	//get facility by city
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Returns Facility Details By City", notes = "JSON Supported", response = Facility.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "success", response = Facility.class),
			@ApiResponse(code = 400, message = "bad-request", response = ErrorDetails.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorDetails.class),
			@ApiResponse(code = 403, message = "Facility service requires authentication - please check username and password", response = ErrorDetails.class),
			@ApiResponse(code = 404, message = "Data not found", response = ErrorDetails.class),
			@ApiResponse(code = 405, message = "Method not allowed", response = ErrorDetails.class),
			@ApiResponse(code = 500, message = "Internal server error", response = ErrorDetails.class) })
	@GetMapping("/facility/city/{facilityCity}")
	public ResponseEntity<List<Facility>> getFacilityByCity(
			@ApiParam(value = "Facility City", required = true) @PathVariable("facilityCity") String facilityCity) {
		List<Facility> facility = facilityServiceImpl.getFacilityByCity(facilityCity);
		return new ResponseEntity<List<Facility>>(facility, new HttpHeaders(), HttpStatus.OK);
	}
	
	//get facility by name
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Returns Facility Details By Name", notes = "JSON Supported", response = Facility.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "success", response = Facility.class),
			@ApiResponse(code = 400, message = "bad-request", response = ErrorDetails.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorDetails.class),
			@ApiResponse(code = 403, message = "Facility service requires authentication - please check username and password", response = ErrorDetails.class),
			@ApiResponse(code = 404, message = "Data not found", response = ErrorDetails.class),
			@ApiResponse(code = 405, message = "Method not allowed", response = ErrorDetails.class),
			@ApiResponse(code = 500, message = "Internal server error", response = ErrorDetails.class) })
	@GetMapping("/facility/facility-name/{facilityName}")
	public ResponseEntity<List<Facility>> getFacilityByName(
			@ApiParam(value = "Facility Name", required = true) @PathVariable("facilityName") String facilityName) {
		List<Facility> facility = facilityServiceImpl.getFacilityByName(facilityName);
		return new ResponseEntity<List<Facility>> (facility, new HttpHeaders(), HttpStatus.OK);
	}
	
	//get by postal code
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Returns Facility Details By Postal Code", notes = "JSON Supported", response = Facility.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "success", response = Facility.class),
			@ApiResponse(code = 400, message = "bad-request", response = ErrorDetails.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorDetails.class),
			@ApiResponse(code = 403, message = "Facility service requires authentication - please check username and password", response = ErrorDetails.class),
			@ApiResponse(code = 404, message = "Data not found", response = ErrorDetails.class),
			@ApiResponse(code = 405, message = "Method not allowed", response = ErrorDetails.class),
			@ApiResponse(code = 500, message = "Internal server error", response = ErrorDetails.class) })
	@GetMapping("/facility/postal-code/{postalCode}")
	public ResponseEntity<List<Facility>> getFacilityByPostalCode(
			@ApiParam(value = "Facility Postal Code", required = true) @PathVariable("postalCode") String postalCode) {
		List<Facility> facility = facilityServiceImpl.getFacilityByCode(postalCode);
		return new ResponseEntity<List<Facility>> (facility, new HttpHeaders(), HttpStatus.OK);
	}

	//return facilty by state
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Returns Facility Details By State", notes = "JSON Supported", response = Facility.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "success", response = Facility.class),
			@ApiResponse(code = 400, message = "bad-request", response = ErrorDetails.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorDetails.class),
			@ApiResponse(code = 403, message = "Facility service requires authentication - please check username and password", response = ErrorDetails.class),
			@ApiResponse(code = 404, message = "Data not found", response = ErrorDetails.class),
			@ApiResponse(code = 405, message = "Method not allowed", response = ErrorDetails.class),
			@ApiResponse(code = 500, message = "Internal server error", response = ErrorDetails.class) })
	@GetMapping("/facility/state/{facilityState}")
	public ResponseEntity<List<Facility>> getFacilityByState(
			@ApiParam(value = "Facility State", required = true) @PathVariable("facilityState") String facilityState) {
		List<Facility> facility = facilityServiceImpl.getFacilityByState(facilityState);
		return new ResponseEntity<List<Facility>> (facility, new HttpHeaders(), HttpStatus.OK);
	}

	//return the facility by country
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Returns Facility Details By Country", notes = "JSON Supported", response = Facility.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "success", response = Facility.class),
			@ApiResponse(code = 400, message = "bad-request", response = ErrorDetails.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorDetails.class),
			@ApiResponse(code = 403, message = "Facility service requires authentication - please check username and password", response = ErrorDetails.class),
			@ApiResponse(code = 404, message = "Data not found", response = ErrorDetails.class),
			@ApiResponse(code = 405, message = "Method not allowed", response = ErrorDetails.class),
			@ApiResponse(code = 500, message = "Internal server error", response = ErrorDetails.class) })
	@GetMapping("/facility/country/{facilityCountry}")
	public ResponseEntity<List<Facility>> getFacilityByCountry(
			@ApiParam(value = "Facility Country", required = true) @PathVariable("facilityCountry") String facilityCountry) {
		List<Facility> facility = facilityServiceImpl.getFacilityByCountry(facilityCountry);
		return new ResponseEntity<List<Facility>> (facility, new HttpHeaders(), HttpStatus.OK);
	}
	
	//return facility by phone number
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Returns Facility Details By Phone", notes = "JSON Supported", response = Facility.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "success", response = Facility.class),
			@ApiResponse(code = 400, message = "bad-request", response = ErrorDetails.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorDetails.class),
			@ApiResponse(code = 403, message = "Facility service requires authentication - please check username and password", response = ErrorDetails.class),
			@ApiResponse(code = 404, message = "Data not found", response = ErrorDetails.class),
			@ApiResponse(code = 405, message = "Method not allowed", response = ErrorDetails.class),
			@ApiResponse(code = 500, message = "Internal server error", response = ErrorDetails.class) })
	@GetMapping("/facility/phone/{facilityPhone}")
	public ResponseEntity<List<Facility>> getFacilityByPhone(
			@ApiParam(value = "Facility Phone", required = true) @PathVariable("facilityPhone") String facilityPhone) {
		List<Facility> facility = facilityServiceImpl.getFacilityByPhone(facilityPhone);
		return new ResponseEntity<List<Facility>> (facility, new HttpHeaders(), HttpStatus.OK);
	}
	
	//get details by facility manager
	//one manager may manage more than one facility
	//or same name of manager for more that one facility, thus return a list
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Returns Facility Details By Facility Manager", notes = "JSON Supported", response = Facility.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "success", response = Facility.class),
			@ApiResponse(code = 400, message = "bad-request", response = ErrorDetails.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorDetails.class),
			@ApiResponse(code = 403, message = "Facility service requires authentication - please check username and password", response = ErrorDetails.class),
			@ApiResponse(code = 404, message = "Data not found", response = ErrorDetails.class),
			@ApiResponse(code = 405, message = "Method not allowed", response = ErrorDetails.class),
			@ApiResponse(code = 500, message = "Internal server error", response = ErrorDetails.class) })
	@GetMapping("/facility/manager/{facilityManager}")
	public ResponseEntity<List<Facility>> getFacilityByFacilityManager(
			@ApiParam(value = "Facility Manager", required = true) @PathVariable("facilityManager") String facilityManager) {
		List<Facility> facility = facilityServiceImpl.getFacilityByFacilityManager(facilityManager);
		return new ResponseEntity<List<Facility>> (facility, new HttpHeaders(), HttpStatus.OK);
	}
	
	//facility details via category
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Returns Facility Details By Category", notes = "JSON Supported", response = Facility.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "success", response = Facility.class),
			@ApiResponse(code = 400, message = "bad-request", response = ErrorDetails.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorDetails.class),
			@ApiResponse(code = 403, message = "Facility service requires authentication - please check username and password", response = ErrorDetails.class),
			@ApiResponse(code = 404, message = "Data not found", response = ErrorDetails.class),
			@ApiResponse(code = 405, message = "Method not allowed", response = ErrorDetails.class),
			@ApiResponse(code = 500, message = "Internal server error", response = ErrorDetails.class) })
	@GetMapping("/facility/category/{facilityCategory}")
	public ResponseEntity<List<Facility>> getFacilityByCategory(
			@ApiParam(value = "Facility Category", required = true) @PathVariable("facilityCategory") String facilityCategory) {
		List<Facility> facility = facilityServiceImpl.getFacilityByCategory(facilityCategory);
		return new ResponseEntity<List<Facility>>(facility,  new HttpHeaders(), HttpStatus.OK);
	}
	
}
