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
@Table(name = "WEEKWORD")
public class WeekWord implements Serializable {

	private static final long serialVersionUID = -1139838270144095544L;

	@Id
	@Column
	private String idx;

	@Column
	private String word_group;

	@Column
	private String word;

	@Column
	private String insert_date;
	@Column
	private String start_date;
	@Column
	private String end_date;

}
