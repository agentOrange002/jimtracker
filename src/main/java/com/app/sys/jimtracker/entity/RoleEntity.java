package com.app.sys.jimtracker.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="roles")
public class RoleEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7039743640943610154L;
	
	@Getter @Setter
	@Id
	@SequenceGenerator(name="role_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")	
	private long id;
	
	@Getter @Setter
	@Column(nullable=false, length=150)
	private String name;
	
	@Getter @Setter
	@ManyToMany(mappedBy="roles")
	private Collection<UserEntity> users;
	
	@Getter @Setter
	@JsonManagedReference
	@ManyToMany(cascade= { CascadeType.PERSIST }, fetch = FetchType.EAGER )
	@JoinTable(name="roles_authorities", 
			joinColumns=@JoinColumn(name="roles_id",referencedColumnName="id"), 
			inverseJoinColumns=@JoinColumn(name="authorities_id",referencedColumnName="id"))
	private Collection<AuthorityEntity> authorities;
	
	public RoleEntity() {}
	
	public RoleEntity(String name) {
		 this.name = name;
	}

}
