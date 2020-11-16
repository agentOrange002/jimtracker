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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.app.sys.jimtracker.generator.IDGenerator;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity(name="tasks")
public class TaskEntity implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 811259616998290847L;
	

	@Id
	@Getter	@Setter	
	@SequenceGenerator(name="task_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq")
    @GenericGenerator(
        name = "task_seq", 
        strategy = "com.app.sys.jimtracker.generator.IDGenerator", 
        parameters = {
            @Parameter(name = IDGenerator.INCREMENT_PARAM, value = "1"),
            @Parameter(name = IDGenerator.VALUE_PREFIX_PARAMETER, value = "TASKID"),
            @Parameter(name = IDGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
    private String id;
	
	@Getter @Setter
	private String taskId;
	
	@Getter	@Setter	
	private Date dateOpened;
	
	@Getter	@Setter	
	private Date dateClosed;	
	
	@Getter	@Setter	
	private String subject;
	
	@Getter	@Setter	
	private String description;
	
	@Getter @Setter
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="ticket_id")
	private TicketEntity ticketDetails;
	
	@Getter	@Setter
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="users_id")	
	private UserEntity usertaskDetails;

}
