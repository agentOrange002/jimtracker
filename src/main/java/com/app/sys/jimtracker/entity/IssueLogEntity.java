package com.app.sys.jimtracker.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Entity(name="issue_logs")
public class IssueLogEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7226217506043439770L;
	
	@Getter @Setter
	@Id
	@SequenceGenerator(name="issuelogs_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "issuelogs_seq")
	private long id;
	
	@Getter @Setter
	private String issueLogId;
	
	@Getter @Setter
	private String issueMessage;
	
	@Getter @Setter
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date logDate;	
	
	@Getter @Setter
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserEntity issueLogUserDetails;	
	
	@Getter @Setter
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="issue_id")
	private IssueEntity issueDetails;

}
