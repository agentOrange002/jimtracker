package com.app.sys.jimtracker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.sys.jimtracker.dto.DashboardDto;
import com.app.sys.jimtracker.repository.IssueRepository;
import com.app.sys.jimtracker.repository.TaskRepository;
import com.app.sys.jimtracker.repository.TicketRepository;
import com.app.sys.jimtracker.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	IssueRepository issueRepository;
	
	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	TaskRepository taskRepository;
	
	@Override
	public DashboardDto getDashboard() {
		DashboardDto returnDto = new DashboardDto();		
		returnDto.setTotalOpenedIssues(issueRepository.countByIssueStatus("OPENED"));
		returnDto.setTotalClosedIssues(issueRepository.countByIssueStatus("CLOSED"));
		returnDto.setTotalCreatedIssues(issueRepository.countByIssueStatus("CREATED"));
		
		returnDto.setTotalOpenedTickets(ticketRepository.countByDateOpenedNotNull());
		returnDto.setTotalClosedTickets(ticketRepository.countByDateClosedNotNull());
		
		returnDto.setTotalOpenedTasks(taskRepository.countByDateOpenedNotNull());
		returnDto.setTotalClosedTasks(taskRepository.countByDateClosedNotNull());
		return returnDto;
	}

}
