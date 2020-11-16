package com.app.sys.jimtracker.ui.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AssignSupportRequestModel 
{	
	private String issueId;
	private String userId;
	private String categoryName;
}
