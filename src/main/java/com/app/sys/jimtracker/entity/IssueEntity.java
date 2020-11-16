package com.app.sys.jimtracker.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.app.sys.jimtracker.generator.IDGenerator;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(name="issues")
@ToString
public class IssueEntity implements Serializable {
	private static final long serialVersionUID = -8712606982967516403L; 
	/*
	 * @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "issue_seq")
	 */
	
	@Id
	@Getter	@Setter	
	@SequenceGenerator(name="issue_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "issue_seq")
    @GenericGenerator(
        name = "issue_seq", 
        strategy = "com.app.sys.jimtracker.generator.IDGenerator", 
        parameters = {
            @Parameter(name = IDGenerator.INCREMENT_PARAM, value = "1"),
            @Parameter(name = IDGenerator.VALUE_PREFIX_PARAMETER, value = "IID"),
            @Parameter(name = IDGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
    private String id;
	
	@Column
	@Getter	@Setter	
	private String issueId;	
	
	@Column(nullable=false)
	@Getter	@Setter	
	private String subject;
	
	@Column(nullable=false)
	@Getter	@Setter
	private String description;
	
	@Column(nullable=false)
	@Getter	@Setter
	private String reportedBy;
	
	@Column(nullable=false)
	@Getter	@Setter
	private String emailProvided;
	
	@Column
	@Getter	@Setter
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date dateReported;
	
	@Getter	@Setter
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="category_id")	
	private CategoryEntity categoryDetails;
	
	
	@Getter	@Setter
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="users_id")
	private UserEntity issueUserDetails;	
	
	@Getter	@Setter
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="supports_id")
	private UserEntity supportUserDetails;	
	
	@Column
	@Getter	@Setter
	private Date dateOpened;
	
	@Column
	@Getter	@Setter
	private Date dateClosed;
	
	@Column
	@Getter	@Setter
	private String issueStatus;
	
	@Getter	@Setter
	@OneToMany(mappedBy="issueDetails", cascade=CascadeType.ALL)
	private List<IssueLogEntity> issueLogs;
	
	@Getter	@Setter
	@OneToMany(mappedBy="issueTickets", cascade=CascadeType.ALL)
	private List<TicketEntity> tickets;
	
}
