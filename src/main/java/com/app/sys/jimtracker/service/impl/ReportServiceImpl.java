package com.app.sys.jimtracker.service.impl;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.app.sys.jimtracker.entity.IssueEntity;
import com.app.sys.jimtracker.repository.IssueRepository;
import com.app.sys.jimtracker.service.ReportService;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	IssueRepository issueRepository;
	
	@Autowired
	@Qualifier("newDataSource")
	DataSource datasource;

	Connection conn = null; 
	

	@Override
	public byte[] generatePDF(String issueId) {
		byte[] bytes = null;
		/*
		 * DataSource dataSource = (DataSource)
		 * SpringApplicationContext.getBean("newDataSource");
		 */
		try {
			conn = datasource.getConnection();
			File file = ResourceUtils.getFile("classpath:reports/issue_report.jrxml");
			JasperDesign jdIIReport = JRXmlLoader.load(file);
			JasperReport jrIIReport = JasperCompileManager.compileReport(jdIIReport);
			Map<String, Object> param = new HashMap<>();
			param.put("issueid", issueId);
			JasperPrint jpIIReport = JasperFillManager.fillReport(jrIIReport, param, conn);		
			//jpIIReport.setName("/"+"Issue_Report_PDF");
			bytes = JasperExportManager.exportReportToPdf(jpIIReport);	
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}

	@Override
	public byte[] generateIssueInfoReport(String id) {
		byte[] bytes = null;
		
		IssueEntity entity = issueRepository.findIssueByIssueId(id);
		List<IssueEntity> collection = new ArrayList<IssueEntity>();
		collection.add(entity);		
		
		try {
			File file = ResourceUtils.getFile("classpath:reports/IssueInfo.jrxml");			
			JRBeanCollectionDataSource data = new JRBeanCollectionDataSource(collection);
			JasperDesign jdReport = JRXmlLoader.load(file);
			JasperReport jrReport = JasperCompileManager.compileReport(jdReport);			
			JasperPrint jpReport = JasperFillManager.fillReport(jrReport, null, data);
			bytes = JasperExportManager.exportReportToPdf(jpReport);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}	
}
