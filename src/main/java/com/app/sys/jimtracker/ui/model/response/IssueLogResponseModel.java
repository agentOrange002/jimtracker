package com.app.sys.jimtracker.ui.model.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

public class IssueLogResponseModel 
{
	
	@Getter @Setter
	private String issueLogId;
	
	@Getter @Setter
	private String issueMessage;
	
	@Getter @Setter
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date logDate;	
	
	@Getter @Setter
	private ShortUserResponseModel userResponseModel;
	
	@Getter @Setter
	private IssueResponseModel issueResponseModel;
}
