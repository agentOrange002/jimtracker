package com.app.sys.jimtracker.service;

public interface ReportService {
	byte[] generatePDF(String issueId);
	byte[] generateIssueInfoReport(String id);
}
