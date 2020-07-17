package com.competition.jpa.model.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name ="MERCURY_TB_USER_ROLE")
public class UserRole implements Serializable {

	private static final long serialVersionUID = 6038453839096370467L;

	@Id
	@Column
	private String idx;
	
	@Column
	private String userIdx;
	
	@Column
	private String userName;
	
	@Column
	private String roleIdx;
	
	@Column
	private String roleName;

}
