package com.app.sys.jimtracker.service;

import java.util.Collection;
import java.util.List;

import com.app.sys.jimtracker.dto.RoleDto;
import com.app.sys.jimtracker.entity.AuthorityEntity;
import com.app.sys.jimtracker.entity.RoleEntity;

public interface RoleService {
	RoleDto saveRole(RoleDto roleDto);
	RoleEntity createRole(String name, Collection<AuthorityEntity> authorities);
	List<RoleDto> getAllRole();
	
}
