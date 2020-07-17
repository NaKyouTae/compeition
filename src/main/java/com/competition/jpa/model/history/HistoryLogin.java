package com.competition.jpa.model.history;

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
@Table(name = "MERCURY_TB_HISTORY_LOGIN")
public class HistoryLogin implements Serializable {

	private static final long serialVersionUID = -6580695243567475799L;
	
	@Id
	@Column
	private String idx;
	
	@Column
	private String accessDate;
	
	@Column
	private String browser;
	
	@Column
	private String userName;
	
	@Column
	private String userIdx;
	
}
