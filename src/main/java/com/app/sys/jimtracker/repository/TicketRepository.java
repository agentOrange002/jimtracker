package com.app.sys.jimtracker.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.sys.jimtracker.entity.IssueEntity;
import com.app.sys.jimtracker.entity.TicketEntity;
import com.app.sys.jimtracker.entity.UserEntity;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Long> {	
	List<TicketEntity> findAllByIssueTickets(IssueEntity issueEntity);	
	List<TicketEntity> findAllByUserticketDetails(UserEntity userEntity);
	TicketEntity findByTicketId(String ticketId);
	List<TicketEntity> findAllByIssueTicketsAndDateClosed(IssueEntity issueEntity, Date date);	
	List<TicketEntity> findAllByIssueTicketsAndDateOpenedAndDateClosed(IssueEntity issueEntity, Date dateopen,Date dateclose);	

	long countByDateOpenedNotNull();
	long countByDateClosedNotNull();
}
