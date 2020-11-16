package com.app.sys.jimtracker.ui.model.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DocumentIssueResponseModel {
	
	private String id;
	
	private String issueId;
	
	private String subject;
	
	private String description;
	
	private String reportedBy;
	
	private String emailProvided;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date dateReported;
	
	private String category;
	
	private String issueUserDetails;
	
	private String supportUserDetails;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date dateOpened;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date dateClosed;
	
	private String issueStatus;
}
