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
@Table(name = "MERCURY_TB_CASH_REQUEST")
public class CashRequest implements Serializable {
	
	private static final long serialVersionUID = -3858968337839809115L;

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
	 * 이전 금액
	 */
	@Column
	private Integer prevCash;

	/**
	 * 잔액
	 */
	@Column
	private Integer afterCash;
	
	/**
	 * 요청 일자
	 */
	@Column 
	private String withDrawDate;
	
	/**
	 * 요청 금액
	 */
	@Column
	private Integer withDrawCash;
	
	/**
	 * 지급 일자
	 */
	@Column
	private String paymentDate;
	
	/**
	 * 승인 여부 
	 */
	@Column
	private Boolean approval; 
	
	/**
	 * 은행 명
	 */
	@Column
	private String bank;
	
	/**
	 * 계좌 번호
	 */
	@Column
	private String account;
	
}
