package com.competition.jpa.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@ToString
@Entity
@Table
public class User implements Serializable {
	
	private static final long serialVersionUID = 2573586487788458557L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;
	
	// 사용자 명
	@Column
	private String username;

	// 사용자 비밀번호
	@Column
	private String pw;
		
	// 가입 일자 yyyy-mm-dd
	@Column
	private LocalDateTime insert_date;
	
	@Column
	private String role;
		
}
