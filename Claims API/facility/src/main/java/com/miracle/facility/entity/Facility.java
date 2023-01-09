package com.miracle.facility.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "facility")
@JsonInclude(value = Include.NON_NULL)
public class Facility {

	
	@Id
	private String _id;

	@ApiModelProperty(required = true, value = "facility id", name = "facilityId", dataType = "String", example = "LAA00000")
	@Field("facility_id")
	private String facilityId;

	@ApiModelProperty(required = true, value = "address line 1", name = "addressLine1", dataType = "String", example = "1415 North Raymond Avenue")
	@Field("address_line_1")
	private String addressLine1;

	@ApiModelProperty(required = false, value = "address line 2", name = "addressLine2", dataType = "String", example = "Suite 100")
	@Field("address_line_2")
	private String addressLine2;

	@ApiModelProperty(required = false, value = "address line 3", name = "addressLine3", dataType = "String", example = "")
	@Field("address_line_3")
	private String addressLine3;

	@ApiModelProperty(required = true, value = "city", name = "city", dataType = "String", example = "Anaheim")
	@Field("city")
	private String city;

	@ApiModelProperty(required = true, value = "name of the facility", name = "facilityName", dataType = "String", example = "Anaheim, CA (PWR)")
	@Field("facility_name")
	private String facilityName;

	@ApiModelProperty(required = true, value = "postal code", name = "postalCode", dataType = "String", example = "92801")
	@Field("postal_code")
	private String postalCode;

	@ApiModelProperty(required = true, value = "state", name = "state", dataType = "String", example = "CA")
	@Field("state")
	private String state;

	@ApiModelProperty(required = true, value = "country", name = "country", dataType = "String", example = "United States")
	@Field("country")
	private String country;

	@ApiModelProperty(required = true, value = "phone", name = "phone", dataType = "String", example = "(714) 449-2880")
	@Field("phone")
	private String phone;

	@ApiModelProperty(required = true, value = "name of facility manager", name = "facilityManager", dataType = "String", example = "Giff Leynagh")
	@Field("facility_manager")
	private String facilityManager;

	@ApiModelProperty(required = true, value = "category of the facility", name = "category", dataType = "String", example = "Blast Freezing")
	@Field("category")
	private String category;
	
	@ApiModelProperty(required = true, value = "create date", name = "created date", dataType = "String", example = "")
	@Field("create_date")
	private String createdDate;

	public String getId() {
		return _id;
	}

	public void setId(String _id) {
		this._id = _id;
	}

	public String getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(String facilityId) {
		this.facilityId = facilityId;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFacilityManager() {
		return facilityManager;
	}

	public void setFacilityManager(String facilityManager) {
		this.facilityManager = facilityManager;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Facility [_id=" + _id + ", facilityId=" + facilityId + ", addressLine1=" + addressLine1
				+ ", addressLine2=" + addressLine2 + ", addressLine3=" + addressLine3 + ", city=" + city
				+ ", facilityName=" + facilityName + ", postalCode=" + postalCode + ", state=" + state + ", country="
				+ country + ", phone=" + phone + ", facilityManager=" + facilityManager + ", category=" + category
				+ "]";
	}

}
