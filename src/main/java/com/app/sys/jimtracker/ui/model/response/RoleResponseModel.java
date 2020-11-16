package com.app.sys.jimtracker.ui.model.response;

import java.util.Collection;

import com.app.sys.jimtracker.entity.AuthorityEntity;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class RoleResponseModel {
	private long id;
	private String name;
	private Collection<AuthorityEntity> authorities;
}
