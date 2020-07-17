package com.mercury.jpa.model.history;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name = "MERCURY_TB_HISTORY_REQUEST")
public class HistoryRequest implements Serializable {

	private static final long serialVersionUID = -4687813483521415848L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long idx;
	
	@Column
	private String ip;
	
	@Column
	private String method;
	
	@Column
	private String username;
	
	@Column
	private String requestUrl;
	
	@Column
	private String browser;
	
	@Lob
	@Column(columnDefinition = "text")
	private String message;
	
	@Column
	private Date requestDate;
}
