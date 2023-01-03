package com.miracle.login.beans;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "refreshtoken")
public class RefreshToken {
  @Id
  private String id;

  private User user;

  private String token;


public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}

public String getToken() {
	return token;
}

public void setToken(String token) {
	this.token = token;
}

public Instant getExpiryDate() {
	return expiryDate;
}

public void setExpiryDate(Instant expiryDate) {
	this.expiryDate = expiryDate;
}

private Instant expiryDate;


}