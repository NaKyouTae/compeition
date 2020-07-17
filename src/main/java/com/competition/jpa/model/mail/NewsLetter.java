package com.competition.jpa.model.mail;

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
@Table(name="MERCURY_TB_NEWSLETTER")
public class NewsLetter implements Serializable {

	private static final long serialVersionUID = 1975656272170506288L;
	
	@Id
	@Column
	private String idx;
	
	@Column
	private String subscribeDate;
	
	@Column
	private String userName;
	
	@Column
	private String userIdx;
	
	@Column
	private String userEMail;
}
