package com.app.sys.jimtracker.ui.model.request;

import lombok.Getter;
import lombok.Setter;

public class PasswordResetModel
{
	@Getter @Setter
	private String token;
	
	@Getter @Setter
	private String password;
}
