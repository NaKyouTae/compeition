package com.mercury.jpa.model.cash;

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
@Table(name = "MERCURY_TB_CASH")
public class Cash implements Serializable {
	
	private static final long serialVersionUID = 429492698367141650L;
	
	
	/**
	 * 일렬 번호
	 */
	@Id
	@Column
	private String idx;
	
	/**
	 * 사용자 명
	 */
	@Column
	private String userName;
	
	/**
	 * 지급 내용
	 */
	@Column
	private String content;
	
	/**
	 * 지급 일자
	 */
	@Column
	private String paymentDate;
	
	/**
	 * 잔액
	 */
	@Column
	private Integer afterCash;	
	
}
