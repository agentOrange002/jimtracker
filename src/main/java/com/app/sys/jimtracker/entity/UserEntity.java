package com.app.sys.jimtracker.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "users")
public class UserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1441957085019368L;

	@Getter
	@Setter
	@Id
	@SequenceGenerator(name = "user_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	private long id;

	@Getter
	@Setter
	@Column(nullable = false)
	private String userId;

	@Getter
	@Setter
	@Column(nullable = false, length = 50)
	private String firstName;

	@Getter
	@Setter
	@Column(nullable = false, length = 50)
	private String middleName;

	@Getter
	@Setter
	@Column(nullable = false, length = 50)
	private String lastName;

	@Getter
	@Setter
	@Column(nullable = false, length = 150, unique = true)
	private String fullName;

	@Getter
	@Setter
	@Column(nullable = false, length = 150, unique = true)
	private String email;

	@Getter
	@Setter
	@Column(nullable = false)
	private String encryptedPassword;

	@Getter
	@Setter
	private String emailVerificationToken;

	@Getter
	@Setter
	@Column(nullable = false, columnDefinition = "boolean default false")
	private Boolean emailVerificationStatus;

	/*
	 * @OneToOne(mappedBy="userImageDetails",cascade = CascadeType.ALL, fetch =
	 * FetchType.LAZY, optional = false)
	 */
	@Getter
	@Setter
	@OneToOne(mappedBy = "userImageDetails", cascade = CascadeType.ALL)
	private UserImageEntity userImage;

	@Getter
	@Setter
	@JsonManagedReference
	@OneToMany(mappedBy = "userticketDetails", cascade = CascadeType.ALL)
	private List<TicketEntity> tickets;

	@Getter
	@Setter
	@JsonManagedReference
	@OneToMany(mappedBy = "usertaskDetails", cascade = CascadeType.ALL)
	private List<TaskEntity> tasks;

	@Getter
	@Setter
	@JsonManagedReference
	@OneToMany(mappedBy = "issueUserDetails", cascade = CascadeType.ALL)
	private List<IssueEntity> issues;

	@Getter
	@Setter
	@JsonManagedReference
	@OneToMany(mappedBy = "issueLogUserDetails", cascade = CascadeType.ALL)
	private List<IssueLogEntity> issuesLogs;

	@Getter
	@Setter
	@JsonManagedReference
	@OneToMany(mappedBy = "supportUserDetails", cascade = CascadeType.ALL)
	private List<IssueEntity> supportissues;

	@Getter
	@Setter
	@JsonManagedReference
	@OneToMany(mappedBy = "userDetails", cascade = CascadeType.ALL)
	private List<AddressEntity> addresses;

	@Getter
	@Setter
	@ManyToMany(cascade = { CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"))
	private Collection<RoleEntity> roles;

}
