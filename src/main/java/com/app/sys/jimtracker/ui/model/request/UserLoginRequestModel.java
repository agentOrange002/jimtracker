package com.app.sys.jimtracker.ui.model.request;

import lombok.Getter;
import lombok.Setter;

public class UserLoginRequestModel
{
	@Getter
	@Setter
	private String email;
	
	@Getter
	@Setter
	private String password;
}
