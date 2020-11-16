package com.app.sys.jimtracker.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class AddressDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3057078777939597994L;

	
	@Getter @Setter	
	private long id;
	
	@Getter @Setter	
	private String addressId;
	
	@Getter @Setter	
	private String city;
	
	@Getter @Setter	
	private String country;
	
	@Getter @Setter	
	private String streetName;
	
	@Getter @Setter	
	private String postalCode;
	
	@Getter @Setter	
	private String type;
	
	@Getter @Setter	
	private UserDto userDetails;
}
