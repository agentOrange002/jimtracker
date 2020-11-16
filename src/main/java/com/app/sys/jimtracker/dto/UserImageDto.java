package com.app.sys.jimtracker.dto;

import java.io.Serializable;

import com.app.sys.jimtracker.entity.UserEntity;

import lombok.Getter;
import lombok.Setter;

public class UserImageDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8583099985724806240L;

	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private UserEntity userImageDetails;

	@Getter
	@Setter
	private String imageId;

	@Getter
	@Setter
	private byte[] image;

}
