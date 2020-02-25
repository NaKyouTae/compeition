package com.competition.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@ToString
@Entity
@Table
public class UserMappingRole implements Serializable {

	private static final long serialVersionUID = 2501922134200250902L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String username;
	
	@Column
	private String rolename;

}
