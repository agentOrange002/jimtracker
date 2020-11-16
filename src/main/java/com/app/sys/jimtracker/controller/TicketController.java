package com.app.sys.jimtracker.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.sys.jimtracker.dto.TicketDto;
import com.app.sys.jimtracker.service.TicketService;
import com.app.sys.jimtracker.ui.model.response.TicketResponseModel;

@RestController
@RequestMapping({ "/api/tickets" })
public class TicketController {
	
	@Autowired
	TicketService ticketService;
	
	@PostMapping(path = "/create/{issueid}/{userid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public TicketResponseModel createTicket(@PathVariable String issueid,@PathVariable String userid) {		
		TicketDto ticketDto = ticketService.createTicket(issueid,userid);		
		TicketResponseModel returnValue = new ModelMapper().map(ticketDto, TicketResponseModel.class);	
		return returnValue;
	}
	
	@GetMapping(path = "/{ticketid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public TicketResponseModel getTicketByTicketId(@PathVariable String ticketid){
		TicketDto dto = ticketService.getTicketByTicketId(ticketid);
		TicketResponseModel returnResponse = new ModelMapper().map(dto, TicketResponseModel.class);
		return returnResponse;
	}
	
	@GetMapping(path = "/all", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<TicketResponseModel> getAllTickets(){
		List<TicketResponseModel> tickets = new ArrayList<TicketResponseModel>();
		List<TicketDto> ticketsDto = ticketService.getAllTickets();
		ModelMapper modelMapper = new ModelMapper();		
		for(TicketDto dto: ticketsDto)	{
			tickets.add(modelMapper.map(dto,TicketResponseModel.class));
		}
		return tickets;
	}
	
	@GetMapping(path = "/all/user/{userid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<TicketResponseModel> getTicketByUserId(@PathVariable String userid){
		List<TicketResponseModel> tickets = new ArrayList<TicketResponseModel>();
		List<TicketDto> ticketsDto = ticketService.getAllTicketsByUserId(userid);
		ModelMapper modelMapper = new ModelMapper();		
		for(TicketDto dto: ticketsDto)	{
			tickets.add(modelMapper.map(dto,TicketResponseModel.class));
		}
		return tickets;
	}
	
	@GetMapping(path = "/all/issue/{issueid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<TicketResponseModel> getTicketByIssueId(@PathVariable String issueid){
		List<TicketResponseModel> tickets = new ArrayList<TicketResponseModel>();
		List<TicketDto> ticketsDto = ticketService.getAllTicketsByIssueId(issueid);
		ModelMapper modelMapper = new ModelMapper();		
		for(TicketDto dto: ticketsDto)	{
			tickets.add(modelMapper.map(dto,TicketResponseModel.class));
		}
		return tickets;
	}
	
	@GetMapping(path = "/all/checkopenedissue/{issueid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<TicketResponseModel> checkedOpenedIssue(@PathVariable String issueid){
		List<TicketResponseModel> tickets = new ArrayList<TicketResponseModel>();
		List<TicketDto> ticketsDto = ticketService.checkOpenedByIssueId(issueid);
		ModelMapper modelMapper = new ModelMapper();		
		for(TicketDto dto: ticketsDto)	{
			tickets.add(modelMapper.map(dto,TicketResponseModel.class));
		}
		return tickets;
	}
	
	@PutMapping(path = "/{ticketid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public TicketResponseModel closeTicket(@PathVariable String ticketid){
		TicketDto dto = ticketService.closeTicket(ticketid);
		TicketResponseModel returnValue = new ModelMapper().map(dto, TicketResponseModel.class);
		return returnValue;
	}

}
