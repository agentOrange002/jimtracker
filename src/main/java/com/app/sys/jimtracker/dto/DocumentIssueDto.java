package com.app.sys.jimtracker.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DocumentIssueDto {
	private String id;
	private String issueId;
	private String subject;
	private String description;
	private String reportedBy;
	private String emailProvided;
	private Date dateReported;
	private String category;
	private String issueUserDetails;		
	private String supportUserDetails;
	private Date dateOpened;
	private Date dateClosed;
	private String issueStatus;
}
