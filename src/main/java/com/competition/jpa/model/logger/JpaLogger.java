package com.competition.jpa.model.logger;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//@Data
//@ToString
@Entity
@Table(name = "LOGGER")
public class JpaLogger {
	
	private static final long serialVersionUID = 9190376302469353485L;

	@Id
	@Column
	private String logger;
	
	@Column
	private Date insertDate;

	@Column
	private String level;
	
	@Column(name="message", length = 5000)
	private String message;
	
}
