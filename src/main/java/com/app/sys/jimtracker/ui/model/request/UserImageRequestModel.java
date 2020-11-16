package com.app.sys.jimtracker.ui.model.request;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class UserImageRequestModel implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2842571018836815951L;	
	
	/*
	 * @Getter @Setter private String userId;
	 */
	
	@Getter	@Setter
	private String image[];
}
