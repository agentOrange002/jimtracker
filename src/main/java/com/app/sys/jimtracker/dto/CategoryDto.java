package com.app.sys.jimtracker.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class CategoryDto implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4781708302093938324L;
	
	@Getter @Setter
	private long id;
	
	@Getter @Setter
	private String categoryId;
	
	@Getter @Setter
	private String name;

}
