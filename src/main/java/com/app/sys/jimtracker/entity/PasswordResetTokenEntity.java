package com.app.sys.jimtracker.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "password_reset_tokens")
public class PasswordResetTokenEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7387975723264098505L;
	
	@Getter @Setter
	@Id
	@GeneratedValue
	private long id;

	@Getter @Setter
	private String token;

	@Getter @Setter
	@OneToOne()
	@JoinColumn(name = "users_id")
	private UserEntity userDetails;

}
