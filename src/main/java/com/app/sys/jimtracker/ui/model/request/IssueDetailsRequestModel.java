package com.app.sys.jimtracker.ui.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssueDetailsRequestModel {	
	private String subject;
	private String description;
	private String reportedBy;
	private String emailProvided;
}
