package com.app.sys.jimtracker.ui.model.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class RoleDetailsRequestModel
{
	@Getter @Setter	
	private String name;
	
	@Getter @Setter	
	private List<String> authorities;
}
