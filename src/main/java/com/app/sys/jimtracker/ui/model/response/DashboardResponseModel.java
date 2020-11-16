package com.app.sys.jimtracker.ui.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DashboardResponseModel {
	private long totalOpenedIssues;
	private long totalOpenedTickets;
	private long totalOpenedTasks;
	
	private long totalCreatedIssues;
	private long totalClosedIssues;
	private long totalClosedTickets;
	private long totalClosedTasks;
}
