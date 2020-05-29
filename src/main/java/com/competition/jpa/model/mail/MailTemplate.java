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
@ToString
@NoArgsConstructor
@Entity
@Table(name="MAILTEMPLATE")
public class MailTemplate implements Serializable{
	
	private static final long serialVersionUID = 7240577151586550963L;
	
	@Id
	@Column
	private String idx;
	
	@Column
	private String type;
	
	@Column
	private String title;
	
	@Column
	private String content;
	
	@Column
	private String insertDate;
	
	@Column
	private String used;
	
	@Column
	private String batchId;	
	
}
