package com.app.sys.jimtracker.ui.model.request;

import lombok.Getter;
import lombok.Setter;

public class ResetPassModel
{
	@Getter @Setter
	public String newPassword;
	
	@Getter @Setter
	public String confirmedPassword;
}
