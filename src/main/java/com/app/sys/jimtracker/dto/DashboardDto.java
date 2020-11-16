package com.app.sys.jimtracker.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DashboardDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 792456526286457082L;
	
	private long totalOpenedIssues;
	private long totalOpenedTickets;
	private long totalOpenedTasks;
	
	private long totalCreatedIssues;
	private long totalClosedIssues;
	private long totalClosedTickets;
	private long totalClosedTasks;
		
}
