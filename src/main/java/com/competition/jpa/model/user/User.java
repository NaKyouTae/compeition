package com.competition.jpa.model.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name = "USER")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1122861054404748086L;

	@Id
	@Column
	private String idx;
	
	@Column
	private String username;
	
	@Column
	private String email;

	@Column
	private String password;
	
	@Column
	private String insertDate;

	@Column
	private String changeDate;
		
}
