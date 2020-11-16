
package com.app.sys.jimtracker.ui.model.response;

import lombok.Getter;
import lombok.Setter;

public class UserImageResponseModel
{
	@Getter	@Setter
	private Long id;	
	
	@Getter	@Setter
	private String imageId;	
	
	@Getter @Setter
	private byte[] image;
}
