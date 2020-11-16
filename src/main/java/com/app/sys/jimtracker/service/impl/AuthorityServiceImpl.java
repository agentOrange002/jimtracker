package com.app.sys.jimtracker.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.sys.jimtracker.dto.AuthorityDto;
import com.app.sys.jimtracker.dto.RoleDto;
import com.app.sys.jimtracker.entity.AuthorityEntity;
import com.app.sys.jimtracker.entity.RoleEntity;
import com.app.sys.jimtracker.entity.UserEntity;
import com.app.sys.jimtracker.repository.AuthorityRepository;
import com.app.sys.jimtracker.repository.RoleRepository;
import com.app.sys.jimtracker.repository.UserRepository;
import com.app.sys.jimtracker.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService {
	
	@Autowired
	AuthorityRepository authorityRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRepository userRepository;


	@Override
	public AuthorityEntity createAuthority(String name) {
		AuthorityEntity authority = authorityRepository.findByName(name);
		if(authority == null) { 	
			authority = new AuthorityEntity(name);
			authorityRepository.save(authority); 
		}
		return authority;
	}

	@Override
	public List<RoleDto> getRolesByUser(String userid) {
		UserEntity user = userRepository.findByUserId(userid);
		List<RoleEntity> listEntity = roleRepository.findAllByUsers(user);
		List<RoleDto> resultList = new ArrayList<RoleDto>();
		ModelMapper mapper = new ModelMapper();
		for(RoleEntity entity: listEntity) {
			resultList.add(mapper.map(entity, RoleDto.class));
		}	
		return resultList;
	}

	@Override
	public List<AuthorityDto> getAuthoritiesByUser(String userid) {
		/*
		 * UserEntity user = userRepository.findByUserId(userid); List<RoleEntity>
		 * listEntity = roleRepository.findAllByUsers(user); //List<RoleDto> resultList
		 * = new ArrayList<RoleDto>(); List<AuthorityDto> authorityList = new
		 * ArrayList<AuthorityDto>();
		 * 
		 * ModelMapper mapper = new ModelMapper(); for(RoleEntity entity: listEntity) {
		 * //resultList.add(mapper.map(entity, RoleDto.class));
		 * 
		 * for(AuthorityEntity a: entity.getAuthorities()) {
		 * authorityList.add(mapper.map(a, AuthorityDto.class)); } } return
		 * authorityList;
		 */
		
		List<AuthorityEntity> list = authorityRepository.findAuthorizationsByUserId(userid);
		List<AuthorityDto> authorityDto = new ArrayList<AuthorityDto>();
		ModelMapper mapper = new ModelMapper();
		for(AuthorityEntity entity: list) {
			authorityDto.add(mapper.map(entity, AuthorityDto.class));
		}		
		return authorityDto;
	}

	

}
