package com.competition.jpa.model.cash;

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
@Table(name = "CASH")
public class Cash implements Serializable {
	
	private static final long serialVersionUID = 429492698367141650L;
	
	
	@Id
	@Column
	private String idx;
	
	@Column
	private String userName;
	
	@Column
	private String why;
	
	@Column 
	private String withDrawDate;
	
	@Column
	private Integer prevCash;
	
	@Column
	private Integer withDrawCash;
	
	@Column
	private Integer afterCash;
	
	@Column
	private String paymentDate;
	
	/**
	 * Null
	 * 신청
	 * 승인
	 */
	@Column
	private String approval; 
	
	@Column
	private String account;
	
}
