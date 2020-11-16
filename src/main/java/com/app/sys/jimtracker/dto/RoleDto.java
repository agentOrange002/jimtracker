package com.app.sys.jimtracker.dto;

import java.io.Serializable;
import java.util.Collection;

import com.app.sys.jimtracker.entity.AuthorityEntity;
import com.app.sys.jimtracker.entity.UserEntity;

import lombok.Getter;
import lombok.Setter;

public class RoleDto implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5180496050075675103L;
	
	@Getter @Setter
	private long id;
	
	@Getter @Setter
	private String name;
	
	@Getter @Setter
	private Collection<UserEntity> users;
	
	@Getter @Setter
	private Collection<AuthorityEntity> authorities;
}
