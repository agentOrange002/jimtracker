package com.app.sys.jimtracker.ui.model.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class UserResponseModel {
	/*
	 * @Getter @Setter private String userId;
	 * 
	 * @Getter @Setter private String firstName;
	 * 
	 * @Getter @Setter private String lastName;
	 * 
	 * @Getter @Setter private String email;
	 */

	@Getter
	@Setter
	private String userId;

	@Getter
	@Setter
	private String firstName;

	@Getter
	@Setter
	private String middleName;

	@Getter
	@Setter
	private String lastName;

	@Getter
	@Setter
	private String fullName;

	@Getter
	@Setter
	private String email;

	@Getter
	@Setter
	private UserImageResponseModel userImage;

	@Getter
	@Setter
	private List<AddressResponseModel> addresses;

}
