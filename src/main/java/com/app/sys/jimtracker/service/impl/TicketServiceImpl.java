package com.app.sys.jimtracker.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.sys.jimtracker.dto.TicketDto;
import com.app.sys.jimtracker.entity.IssueEntity;
import com.app.sys.jimtracker.entity.TicketEntity;
import com.app.sys.jimtracker.entity.UserEntity;
import com.app.sys.jimtracker.exceptions.AppServiceException;
import com.app.sys.jimtracker.repository.IssueRepository;
import com.app.sys.jimtracker.repository.TicketRepository;
import com.app.sys.jimtracker.repository.UserRepository;
import com.app.sys.jimtracker.service.TicketService;
import com.app.sys.jimtracker.tool.Utils;
import com.app.sys.jimtracker.ui.model.response.ErrorMessages;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class TicketServiceImpl implements TicketService {
	
	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	IssueRepository issueRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils utils;	
	
	@Override
	public TicketDto createTicket(String issueid, String userid) {			
		if(issueRepository.findIssueByIssueId(issueid) == null) throw new AppServiceException(ErrorMessages.ISSUE_NOT_FOUND.getErrorMessage());
		if(issueRepository.findByIssueIdAndSupportUserDetailsIsNull(issueid)!=null) throw new  AppServiceException(ErrorMessages.ISSUE_HAS_NO_SUPPORT.getErrorMessage());	
		TicketEntity entity = new TicketEntity();
		IssueEntity issueEntity = issueRepository.findIssueByIssueId(issueid);	
		UserEntity userEntity = userRepository.findByUserId(userid);
		entity.setTicketId(utils.generateTicketId(10));
		entity.setDateOpened(new Date());
		entity.setIssueTickets(issueEntity);
		entity.setUserticketDetails(userEntity);
		TicketEntity saveEntity = ticketRepository.save(entity);		
		return new ModelMapper().map(saveEntity, TicketDto.class);
	}


	@Override
	public List<TicketDto> getAllTickets() {
		List<TicketDto> ticketsDto = new ArrayList<TicketDto>();		
		List<TicketEntity> ticketsEntity = ticketRepository.findAll();
		log.info(ticketsEntity);
		ModelMapper modelMapper = new ModelMapper();	
		for(TicketEntity entity: ticketsEntity) {
			
			ticketsDto.add(modelMapper.map(entity,TicketDto.class));
		}		
		return ticketsDto;
	}


	@Override
	public List<TicketDto> getAllTicketsByUserId(String userid) {
		List<TicketDto> ticketsDto = new ArrayList<TicketDto>();		
		UserEntity userEntity = userRepository.findByUserId(userid);
		List<TicketEntity> ticketsEntity = ticketRepository.findAllByUserticketDetails(userEntity);		
		ModelMapper modelMapper = new ModelMapper();	
		for(TicketEntity entity: ticketsEntity) {
			
			ticketsDto.add(modelMapper.map(entity,TicketDto.class));
		}		
		return ticketsDto;
	}


	@Override
	public List<TicketDto> getAllTicketsByIssueId(String issueid) {
		List<TicketDto> ticketsDto = new ArrayList<TicketDto>();		
		IssueEntity issueEntity = issueRepository.findIssueByIssueId(issueid);
		//List<TicketEntity> ticketsEntity = ticketRepository.findAllByIssueTickets(issueEntity);	
		List<TicketEntity> ticketsEntity = ticketRepository.findAllByIssueTicketsAndDateClosed(issueEntity,null);		
		ModelMapper modelMapper = new ModelMapper();	
		for(TicketEntity entity: ticketsEntity) {
			
			ticketsDto.add(modelMapper.map(entity,TicketDto.class));
		}		
		return ticketsDto;
	}


	@Override
	public TicketDto closeTicket(String ticketid) {
		TicketEntity ticketEntity = ticketRepository.findByTicketId(ticketid);
		if(ticketEntity==null) throw new  AppServiceException(ErrorMessages.TICKETID_NOT_FOUND.getErrorMessage());
		ticketEntity.setDateClosed(new Date());
		TicketEntity updateEntity = ticketRepository.save(ticketEntity);
		TicketDto returnDto = new ModelMapper().map(updateEntity, TicketDto.class);
		return returnDto;
	}


	@Override
	public TicketDto getTicketByTicketId(String ticketid) {
		TicketEntity ticketEntity = ticketRepository.findByTicketId(ticketid);
		if(ticketEntity==null) throw new  AppServiceException(ErrorMessages.TICKETID_NOT_FOUND.getErrorMessage());
		TicketDto returnDto = new ModelMapper().map(ticketEntity, TicketDto.class);
		return returnDto;
	}


	@Override
	public List<TicketDto> checkOpenedByIssueId(String issueid) {
		List<TicketDto> ticketsDto = new ArrayList<TicketDto>();		
		IssueEntity issueEntity = issueRepository.findIssueByIssueId(issueid);
		List<TicketEntity> ticketsEntity = ticketRepository.findAllByIssueTicketsAndDateOpenedAndDateClosed(issueEntity,null,null);		
		ModelMapper modelMapper = new ModelMapper();	
		for(TicketEntity entity: ticketsEntity) {			
			ticketsDto.add(modelMapper.map(entity,TicketDto.class));
		}		
		return ticketsDto;
	}

}
