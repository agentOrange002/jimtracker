package com.app.sys.jimtracker.ui.model.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

public class ErrorMessage 
{
	@Getter @Setter
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date timestamp;
	@Getter @Setter
	private String message;
	
	public ErrorMessage() {}
	
	public ErrorMessage(Date timestamp, String message)
	{
		this.timestamp = timestamp;
		this.message = message;
	}
}
