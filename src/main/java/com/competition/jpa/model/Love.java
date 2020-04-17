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
@Table(name = "LOVE")
public class Love implements Serializable {

	private static final long serialVersionUID = -763568391708728064L;

	@Id
	@Column
	private String idx;

	@Column(name = "insert_date")
	private String insertDate;
		
	@Column(name = "user_idx")
	private String userIdx;

	@Column(name = "content_idx")
	private String contentIdx;
}
