package com.competition.jpa.model.word;

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
@Table(name = "MERCURY_TB_WORD")
public class Word implements Serializable {

	private static final long serialVersionUID = -1139838270144095544L;

	@Id
	@Column
	private String idx;
	@Column
	private String wordGroup;
	@Column
	private String word;
	@Column
	private String description;
	@Column
	private String insertDate;
	@Column
	private String startDate;
	@Column
	private String endDate;

}
