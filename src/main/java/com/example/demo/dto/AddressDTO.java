package com.example.demo.dto;

import javax.validation.constraints.NotBlank; 
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AddressDTO {
	
	@NotNull(message="streetNumber can't be Empty!")
	private Integer streetNumber;
	@Pattern(regexp="^[A-Za-z]*$",message = "City Name must contain only  alphabet")
	@NotBlank(message="city can't be Empty!")
	private String city;
	@Pattern(regexp="^[A-Za-z]*$",message = "State Name must contain only  alphabet")
	@NotBlank(message="state can't be Empty!")
	private String state;
	public Integer getStreetNumber() {
		return streetNumber;
	}
	public void setStreetNumber(Integer streetNumber) {
		this.streetNumber = streetNumber;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	

}
