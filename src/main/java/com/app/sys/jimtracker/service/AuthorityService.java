package com.app.sys.jimtracker.service;

import java.util.List;

import com.app.sys.jimtracker.dto.AuthorityDto;
import com.app.sys.jimtracker.dto.RoleDto;
import com.app.sys.jimtracker.entity.AuthorityEntity;

public interface AuthorityService {
	AuthorityEntity createAuthority(String name);	
	List<RoleDto> getRolesByUser(String userid);
	List<AuthorityDto> getAuthoritiesByUser(String userid);
}
