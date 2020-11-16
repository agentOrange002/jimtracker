package com.app.sys.jimtracker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.sys.jimtracker.dto.DashboardDto;
import com.app.sys.jimtracker.service.DashboardService;
import com.app.sys.jimtracker.ui.model.response.DashboardResponseModel;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

@RestController
@RequestMapping({ "/api/dashboard" })
public class DashboardController {
	
	@Autowired
	DashboardService dashboardService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public DashboardResponseModel getDashboard() {
		DashboardDto dto = dashboardService.getDashboard();	
		return new ModelMapper().map(dto,DashboardResponseModel.class);
	}
	
}
