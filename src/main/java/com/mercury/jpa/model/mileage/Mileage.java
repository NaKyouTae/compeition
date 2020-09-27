package com.mercury.jpa.model.mileage;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "MERCURY_TB_MILEAGE")
public class Mileage implements Serializable {
	
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
	private Integer paymentMileage;	
	
}
