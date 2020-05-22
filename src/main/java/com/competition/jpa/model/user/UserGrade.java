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
@Table(name ="USERGRADE")
public class UserGrade implements Serializable {

	private static final long serialVersionUID = -4309721016901765021L;

	@Id
	@Column
	private String idx;
	
	@Column
	private String userIdx;
	
	@Column
	private String userName;
	
	@Column
	private String gradeIdx;
	
	@Column
	private String gradeName;

}
