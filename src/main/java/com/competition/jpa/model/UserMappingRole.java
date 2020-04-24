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
@Table(name ="USERMAPPINGROLE")
public class UserMappingRole implements Serializable {

	private static final long serialVersionUID = 6038453839096370467L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private Long idx;
	
	@Column
	private String userIdx;
	
	@Column
	private String userName;
	
	@Column
	private String roleIdx;
	
	@Column
	private String roleName;

}
