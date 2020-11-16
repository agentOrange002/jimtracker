package com.app.sys.jimtracker.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(name="addresses")
@ToString
public class AddressEntity implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9161809093285970231L;
	
	@Getter @Setter
	@Id
	@GeneratedValue
	private long id;
	
	@Getter @Setter
	@Column(length=30, nullable=false)
	private String addressId;
	
	@Getter @Setter
	@Column(length=150, nullable=false)
	private String city;
	
	@Getter @Setter
	@Column(length=150, nullable=false)
	private String country;
	
	@Getter @Setter
	@Column(length=100, nullable=false)
	private String streetName;
	
	@Getter @Setter
	@Column(length=100, nullable=false)
	private String postalCode;
	
	@Getter @Setter
	@Column(length=10, nullable=false)
	private String type;
	
	@Getter @Setter
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="users_id")
	private UserEntity userDetails;
}
