package com.competition.jpa.model.cash;

import java.io.Serializable;

import javax.persistence.Column;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Cash implements Serializable {
	
	private static final long serialVersionUID = 429492698367141650L;
	
	
	@Id
	@Column
	private String idx;
	
	@Column
	private String userName;
	
	@Column 
	private String withDrawDate;
	
	@Column
	private String prevCash;
	
	@Column
	private String widthDrawCash;
	
	@Column
	private String afterCash;
	
	@Column
	private String approval; 
	
}
