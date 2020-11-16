package com.app.sys.jimtracker.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TicketDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8367087816973516651L;

	private String id;				

	private String ticketId;	
  
	private Date dateOpened;			
  
	private Date dateClosed;		

	private UserDto userticketDetails;
	
	private IssueDto issueTickets;	
}
