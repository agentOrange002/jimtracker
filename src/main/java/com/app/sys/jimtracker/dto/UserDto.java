package com.app.sys.jimtracker.dto;


import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;


public class UserDto implements Serializable
{
		
	private static final long serialVersionUID = 7391789643842774201L;

	@Getter	@Setter
	private long id;
	
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
	
	@Getter	@Setter
	private String password;
	
	@Getter	@Setter
	private String encryptedPassword;
	
	@Getter	@Setter
	private String emailVerificationToken;
	
	@Getter	@Setter
	private Boolean emailVerificationStatus = false;
	
	@Getter	@Setter
	private UserImageDto userImage;
	
	@Getter	@Setter
	private List<AddressDto> addresses;
	
	
}
