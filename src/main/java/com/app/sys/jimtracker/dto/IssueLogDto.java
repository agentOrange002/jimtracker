package com.app.sys.jimtracker.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class IssueLogDto implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7573302103378139140L;

	@Getter @Setter
	private long id;
	
	@Getter @Setter
	private String issueLogId;
	
	@Getter @Setter
	private String issueMessage;
	
	@Getter @Setter
	private Date logDate;	
	
	@Getter @Setter	
	private UserDto userDto;
	
	@Getter @Setter	
	private IssueDto issueDto;
}
