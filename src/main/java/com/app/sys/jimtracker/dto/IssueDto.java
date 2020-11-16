package com.app.sys.jimtracker.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class IssueDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5314626678040380852L;

	@Getter
	@Setter
	private String id;

	@Getter
	@Setter
	private String issueId;

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

	@Getter
	@Setter
	private Date dateReported;

	@Getter
	@Setter
	private CategoryDto category;

	@Getter
	@Setter
	private UserDto issueUserDetails;

	@Getter
	@Setter
	private UserDto supportUserDetails;

	@Getter
	@Setter
	private Date dateOpened;

	@Getter
	@Setter
	private Date dateClosed;

	@Getter
	@Setter
	private String issueStatus;
}
