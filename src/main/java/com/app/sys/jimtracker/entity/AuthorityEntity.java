package com.app.sys.jimtracker.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="authorities")
public class AuthorityEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3651357756339872677L;
	
	@Id
	@Getter @Setter
	@SequenceGenerator(name="authority_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authority_seq")
	private long id;
	
	@Getter @Setter
	@Column(nullable=false, length=150)
	private String name;
	
	@Getter @Setter
	@JsonBackReference
	@ManyToMany(mappedBy="authorities")
	private Collection<RoleEntity> roles;

	public AuthorityEntity() {}
	
	public AuthorityEntity(String name) {
		 this.name = name;
	}
}
