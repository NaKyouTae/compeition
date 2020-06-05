package com.competition.jpa.model.notice;

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
@Table(name = "NOTICE")
public class Notice implements Serializable {

	private static final long serialVersionUID = 1135979481373964586L;
	
	@Id
	@Column
	private String idx;
	
	@Column
	private String title;
	
	@Column(name="content", length=5000)
	private String content;
	
	@Column
	private String insertDate;
	
	@Column
	private String type;
}
