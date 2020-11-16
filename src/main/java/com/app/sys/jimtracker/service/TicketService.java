package com.app.sys.jimtracker.service;

import java.util.List;

import com.app.sys.jimtracker.dto.TicketDto;

public interface TicketService {
	TicketDto createTicket(String issueid,String userid);
	List<TicketDto> getAllTickets();	
	List<TicketDto> getAllTicketsByUserId(String userid);	
	List<TicketDto> getAllTicketsByIssueId(String issueid);	
	TicketDto closeTicket(String ticketid);
	TicketDto getTicketByTicketId(String ticketid);
	
	List<TicketDto> checkOpenedByIssueId(String issueid);	
}
