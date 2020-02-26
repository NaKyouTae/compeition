package com.competition.jpa.model;

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
@Table(name ="USERMAPPINGROLE")
public class UserMappingRole implements Serializable {

	private static final long serialVersionUID = 1122861054404748086L;

	@Id
	@Column
	private String username;
	
	@Column
	private String rolename;

}
