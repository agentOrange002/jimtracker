package com.app.sys.jimtracker.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AuthorityDto  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3175001798973934658L;
	private long id;	
	private String name;		
}
