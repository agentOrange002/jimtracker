package com.app.sys.jimtracker.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.sys.jimtracker.dto.AuthorityDto;
import com.app.sys.jimtracker.dto.RoleDto;
import com.app.sys.jimtracker.service.AuthorityService;
import com.app.sys.jimtracker.ui.model.response.AuthorityResponseModel;
import com.app.sys.jimtracker.ui.model.response.RoleResponseModel;

@RestController
@RequestMapping({ "/api/authorities" })
public class AuthorityController {

	@Autowired
	AuthorityService authorityService;	

	@GetMapping(path="/roles/{userid}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<RoleResponseModel> getRolesAuthoritiesByUser(@PathVariable String userid) {	
		List<RoleDto> listDto = authorityService.getRolesByUser(userid);
		List<RoleResponseModel> responseList = new ArrayList<RoleResponseModel>();
		ModelMapper mapper = new ModelMapper();
		for(RoleDto dto: listDto) {
			responseList.add(mapper.map(dto, RoleResponseModel.class));
		}
		return responseList;		
	}
	
	@GetMapping(path="/{userid}", produces = {MediaType.APPLICATION_JSON_VALUE})	
	public List<AuthorityResponseModel> getAuthoritiesByUser(@PathVariable String userid) {	
		List<AuthorityDto> listDto = authorityService.getAuthoritiesByUser(userid);
		List<AuthorityResponseModel> responseList = new ArrayList<AuthorityResponseModel>();
		ModelMapper mapper = new ModelMapper();
		for(AuthorityDto dto: listDto) {
			responseList.add(mapper.map(dto, AuthorityResponseModel.class));
		}
		return responseList;		
	}
	
}
