package com.app.sys.jimtracker.ui.model.response;

import lombok.Getter;
import lombok.Setter;

public class ShortUserResponseModel {
	@Getter	@Setter
	private String userId;
	
	@Getter	@Setter
	private String firstName;
	
	@Getter	@Setter
	private String middleName;
	
	@Getter	@Setter
	private String lastName;
	
	@Getter	@Setter
	private String fullName;	
	
	@Getter	@Setter
	private String email;	
	
}
