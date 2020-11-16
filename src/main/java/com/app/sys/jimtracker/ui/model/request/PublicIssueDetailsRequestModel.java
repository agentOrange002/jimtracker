package com.app.sys.jimtracker.ui.model.request;

import lombok.Getter;
import lombok.Setter;

public class PublicIssueDetailsRequestModel 
{
	@Getter
	@Setter
	private String subject;
	
	@Getter
	@Setter
	private String description;
	
	@Getter
	@Setter
	private String reportedBy;
	
	@Getter
	@Setter
	private String emailProvided;
	
}
