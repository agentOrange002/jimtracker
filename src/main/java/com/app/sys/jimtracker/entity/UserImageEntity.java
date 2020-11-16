package com.app.sys.jimtracker.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "user_images")
public class UserImageEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1706020437689761341L;

	@Id
	@GeneratedValue
	@Getter
	@Setter
	@Column
	private Long id;

	@Getter
	@Setter
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "user_id", unique = true)
	private UserEntity userImageDetails;

	@Getter
	@Setter
	@Column(nullable = false)
	private String imageId;

	@Getter
	@Setter
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(length = 100000, columnDefinition = "LONGBLOB")
	private byte[] image;

	@JsonProperty
	public long getUserId() {
		return userImageDetails.getId();
	}
}
