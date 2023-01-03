package com.miracle.login.beans;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "user_details")
@JsonInclude(value = Include.NON_NULL)
public class User {
	@Id
	private String id;
	
	@Field("username")
	private String username;
	
	@Field("password")
	private String password;
	
	@Field("name")
	private String name;
	
	@Field("email")
	private String email;
	
	@Field("phone")
	private String phone;
	
	@Field("wms_account_number")
	private String wmsAccountNumber;
	
	@DBRef
	private Set<Role> roles = new HashSet<>();
	
	public User(String username, String email, String name, String phone, String password) {
		    this.username = username;
		    this.email = email;
		    this.name= name;
		    this.phone=phone;
		    this.password = password;
	}

	
}

//{
//    "_id" : ObjectId("63a1e35a88a09474ec6c3cf9"),
//    "username" : "baleksandrov1c",
//    "password" : "FJMWzZCJnoP",
//    "name" : "Brina Aleksandrov",
//    "email" : "baleksandrov1c@yolasite.com",
//    "phone" : "885-852-2554",
//    "wms_account_number" : "tz1ZShZs9o4jJXS8VAgy9C7LhVWdkLkDcC2A"
//}