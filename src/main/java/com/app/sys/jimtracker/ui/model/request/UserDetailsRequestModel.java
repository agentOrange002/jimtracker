package com.app.sys.jimtracker.ui.model.request;

import lombok.Getter;
import lombok.Setter;

public class UserDetailsRequestModel {
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
	private String password;
}
