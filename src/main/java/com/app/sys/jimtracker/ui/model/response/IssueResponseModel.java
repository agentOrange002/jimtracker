package com.app.sys.jimtracker.ui.model.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

public class IssueResponseModel {
	@Getter @Setter
	private String id;
	
	@Getter @Setter
	private String issueId;
	
	@Getter	@Setter
	private String subject;
	
	@Getter	@Setter
	private String description;
	
	@Getter	@Setter
	private String emailProvided;
	
	@Getter	@Setter
	private String reportedBy;
	
	@Getter @Setter
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date dateReported;	
	
	@Getter @Setter
	private CategoryResponseModel category;
	
	@Getter @Setter
	private ShortUserResponseModel issueUserDetails;
	
	@Getter @Setter
	private ShortUserResponseModel supportUserDetails;
	
	@Getter @Setter
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date dateOpened;
	
	@Getter @Setter
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date dateClosed;
	
	@Getter @Setter
	private String issueStatus;
	
}
