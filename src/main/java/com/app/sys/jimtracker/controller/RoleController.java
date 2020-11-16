package com.app.sys.jimtracker.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.sys.jimtracker.dto.RoleDto;
import com.app.sys.jimtracker.service.RoleService;
import com.app.sys.jimtracker.ui.model.request.RoleRequestModel;
import com.app.sys.jimtracker.ui.model.response.RoleResponseModel;

@RestController
@RequestMapping({"/api/roles"})
public class RoleController {
	
	@Autowired
	RoleService roleService;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public RoleResponseModel postRole(@RequestBody RoleRequestModel requestModel) {		
		RoleDto roleDto = new ModelMapper().map(requestModel, RoleDto.class);
		RoleDto saveroleDto =  roleService.saveRole(roleDto);	
		return new ModelMapper().map(saveroleDto, RoleResponseModel.class);
	}
	
	@GetMapping(path = "/all", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<RoleResponseModel> getRoles() {
		List<RoleDto> listDto = roleService.getAllRole();
		List<RoleResponseModel> responseList = new ArrayList<RoleResponseModel>();
		ModelMapper mapper = new ModelMapper();
		for(RoleDto dto: listDto) {
			responseList.add(mapper.map(dto, RoleResponseModel.class));
		}
		return responseList;
	}	
}
