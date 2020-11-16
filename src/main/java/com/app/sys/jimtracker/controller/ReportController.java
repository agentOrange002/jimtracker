package com.app.sys.jimtracker.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.sys.jimtracker.service.ReportService;

@Controller
@RequestMapping({ "/api/reports" })
public class ReportController {
	@Autowired
	ReportService reportService;

	@GetMapping(path = "/download")
	public ResponseEntity<Resource> download(String param) throws IOException {

		// ...
		File file = new File("C:\\Users\\Administrator\\Desktop\\Logo.png");
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Logo1.png");

		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

		return ResponseEntity.ok().headers(headers).contentLength(file.length())
				.contentType(MediaType.parseMediaType("application/x-download")).body(resource);
	}

	@GetMapping(path="/pdf/{id}")
	public ResponseEntity<byte[]> generatePdf(@PathVariable String id) {

		/*
		 * As Download headers.add(HttpHeaders.CONTENT_DISPOSITION,
		 * "attachment; filename=IssueReport.pdf");
		 */

		/*
		 * As View PDF headers.add("content-disposition", "inline;filename=" + bytes);
		 */

		byte[] bytes = reportService.generatePDF(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=IssueReport.pdf");
		//headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=IssueReport.pdf");
		return ResponseEntity.ok().headers(headers).header("Content-Type", "application/pdf; charset=UTF-8")
				.body(bytes);
	}
	
	@GetMapping(path="/issue/{id}")
	public ResponseEntity<byte[]> generateIssueInfoReport(@PathVariable String id){
		byte[] bytes = reportService.generateIssueInfoReport(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=IssueInfoReport.pdf");
		return ResponseEntity.ok().headers(headers).header("Content-Type", "application/pdf; charset=UTF-8")
				.body(bytes);
	}

	@GetMapping(path = "/upload")
	public ResponseEntity<Resource> upload(String param) throws IOException {
		File file = new File("C:\\Users\\Administrator\\Desktop\\Logo.png");
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=LogoLogo.png");

		return ResponseEntity.ok().headers(headers).contentLength(file.length())
				.contentType(MediaType.parseMediaType("application/x-download")).body(resource);
	}
}
