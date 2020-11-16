package com.app.sys.jimtracker.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="categories")
public class CategoryEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6831316422176181350L;
	
	@Id
	@Getter @Setter
	@SequenceGenerator(name="category_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
	private long id;
	
	@Getter @Setter	
	private String categoryId;
	
	@Getter @Setter
	@Column(unique=true)
	private String name;	
	
	@Getter	@Setter	
	@OneToMany(mappedBy="categoryDetails",cascade = CascadeType.ALL)
	private List<IssueEntity> issues;
}
