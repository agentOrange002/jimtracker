package com.app.sys.jimtracker.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.app.sys.jimtracker.entity.AuthorityEntity;
import com.app.sys.jimtracker.entity.RoleEntity;
import com.app.sys.jimtracker.repository.RoleRepository;

@Component
public class RoleTransaction
{
	private final static Logger logger = LoggerFactory.getLogger(AuthorityTransaction.class);
	
	@Autowired
	RoleRepository roleRepository;
	
	@Transactional
	public Collection<RoleEntity> getRoles(List<String> list)
	{
		Collection<RoleEntity> result = new ArrayList<RoleEntity>();		
		for(String namelist: list) 
		{
			RoleEntity entity = roleRepository.findByName(namelist);
			result.add(entity);
			logger.info("Get RoleEntity :"+entity.toString());
		}	
		return result;
	}
	
	@Transactional 
	private RoleEntity createRole( String name,Collection<AuthorityEntity> authorities) 
	{
		RoleEntity role = roleRepository.findByName(name); 
		if (role == null) 
		{ 
			role = new RoleEntity(name); 
			role.setAuthorities(authorities);
			roleRepository.save(role); 
		} 
		return role; 
	}
}
