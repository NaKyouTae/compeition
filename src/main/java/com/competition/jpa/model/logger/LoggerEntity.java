package com.competition.jpa.model.logger;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "LOGGER")
public class LoggerEntity {
	
	private static final long serialVersionUID = 9190376302469353485L;

	@Id
	@Column
	private long idx;
	
	@Column
	private String logger;
	
	@Column
	private Date insertDate;

	@Column
	private String level;
	
	@Column
	private String ip;
	
	@Column
	private String method;
	
	@Column
	private String user;
	
	@Column
	private String requestUrl;
	
	@Column
	private String browser;
	
	@Lob
	@Column(columnDefinition = "text")
	private String message;
	
}
