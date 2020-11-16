package com.app.sys.jimtracker.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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

import lombok.Getter;
import lombok.Setter;

@Entity(name="tickets")
public class TicketEntity implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5058580566597410049L;

	@Id
	@Getter	@Setter	
	@SequenceGenerator(name="ticket_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_seq")
    @GenericGenerator(
        name = "ticket_seq", 
        strategy = "com.app.sys.jimtracker.generator.IDGenerator", 
        parameters = {
            @Parameter(name = IDGenerator.INCREMENT_PARAM, value = "1"),
            @Parameter(name = IDGenerator.VALUE_PREFIX_PARAMETER, value = "TID"),
            @Parameter(name = IDGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
    private String id;
	
	@Getter @Setter
	private String ticketId;
	
	@Getter	@Setter	
	private Date dateOpened;
	
	@Getter	@Setter	
	private Date dateClosed;	

	@Getter	@Setter
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="users_id")	
	private UserEntity userticketDetails;
	
	@Getter	@Setter
	@OneToMany(mappedBy="ticketDetails", cascade=CascadeType.ALL)
	private List<TaskEntity> tasks;
	
	@Getter @Setter
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="issue_id")
	private IssueEntity issueTickets;	
}
