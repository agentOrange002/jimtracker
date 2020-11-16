package com.app.sys.jimtracker.service;

import java.util.List;

import com.app.sys.jimtracker.dto.IssueLogDto;

public interface IssueLogService
{
	IssueLogDto saveIssueLog(IssueLogDto issueLog, String issueId, String userId);
	List<IssueLogDto> getIssueLogById(String issueId);
}
