package com.competition.jpa.model.logger;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name = "TB_LOG")
public class Logger implements Serializable {
	
	private static final long serialVersionUID = 9190376302469353485L;

	@Column
	private Date insertDate;
	
	@Column
	private String logger;
	
	@Column
	private String level;
	
	@Column(name="message", length = 5000)
	private String message;
	
}
