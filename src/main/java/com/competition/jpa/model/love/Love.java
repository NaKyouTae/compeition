package com.competition.jpa.model.love;

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
@Table(name = "MERCURY_TB_LOVE")
public class Love implements Serializable {

	private static final long serialVersionUID = -763568391708728064L;

	@Id
	@Column
	private String idx;

	@Column
	private String insertDate;
		
	@Column
	private String userIdx;

	@Column
	private String contentIdx;
}
