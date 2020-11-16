package com.app.sys.jimtracker.service;

import java.util.List;

import com.app.sys.jimtracker.dto.DocumentIssueDto;
import com.app.sys.jimtracker.dto.IssueDto;

public interface IssueService {
	boolean publicSaveIssue(IssueDto issue);	
	IssueDto saveIssue(IssueDto issue,String userId);
	IssueDto getIssueByIssueId(String issueId);
	IssueDto updateIssue(String issueId, IssueDto issue);
	void deleteIssue(String issueId);
	List<IssueDto> getIssueList();	
	IssueDto assignSupport(String issueId, String userId, String categoryName);
	IssueDto ownedthisIssue(String issueId, String userId);
	List<DocumentIssueDto> getIssueDocuments(String emailProvided);
}
