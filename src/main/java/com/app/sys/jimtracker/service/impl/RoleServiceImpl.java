package com.app.sys.jimtracker.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.sys.jimtracker.dto.RoleDto;
import com.app.sys.jimtracker.entity.AuthorityEntity;
import com.app.sys.jimtracker.entity.RoleEntity;
import com.app.sys.jimtracker.repository.RoleRepository;
import com.app.sys.jimtracker.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleRepository roleRepository;

	@Override
	public RoleDto saveRole(RoleDto roleDto) {	
		RoleEntity roleEntity = new ModelMapper().map(roleDto, RoleEntity.class);
		RoleEntity saveroleEntity = roleRepository.save(roleEntity);	
		return new ModelMapper().map(saveroleEntity, RoleDto.class);
	}

	@Override
	public RoleEntity createRole(String name, Collection<AuthorityEntity> authorities) {
		RoleEntity role = roleRepository.findByName(name);
		if (role == null) {
			role = new RoleEntity(name);
			role.setAuthorities(authorities);
			roleRepository.save(role);
		}
		return role;
	}

	@Override
	public List<RoleDto> getAllRole() {
		List<RoleEntity> listEntity = roleRepository.findAll();
		List<RoleDto> resultList = new ArrayList<RoleDto>();
		ModelMapper mapper = new ModelMapper();
		for(RoleEntity entity: listEntity) {
			resultList.add(mapper.map(entity, RoleDto.class));
		}	
		return resultList;
	}	
}
