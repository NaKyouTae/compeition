package com.mercury.jpa.model.mail;

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
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="MERCURY_TB_MAIL_TEMPLATE")
public class MailTemplate implements Serializable{
	
	private static final long serialVersionUID = 7240577151586550963L;
	
	/**
	 * 일렬번호
	 */
	@Id
	@Column
	private String idx;
	
	/**
	 * 메일 템플릿 조회 타입
	 */
	@Column
	private String type;
	
	/**
	 * 메일 제목
	 */
	@Column
	private String title;
	
	/**
	 * html 명
	 * ex) username.html
	 */
	@Column
	private String tempName;
	
	/**
	 * 생성일자 yyyy-MM-dd HH:mm
	 */
	@Column
	private String insertDate;
	
}
