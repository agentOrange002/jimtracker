package com.app.sys.jimtracker.ui.model.request;

import lombok.Getter;
import lombok.Setter;

public class AddressRequestModel {
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
}
