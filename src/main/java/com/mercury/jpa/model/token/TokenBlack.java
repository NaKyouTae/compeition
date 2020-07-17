package com.mercury.jpa.model.token;

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
@Table(name = "MERCURY_TB_TOKEN_BLACK")
public class TokenBlack implements Serializable {

	private static final long serialVersionUID = 2288931954818799859L;

	@Id
	@Column
	private String userName;
	
	@Column(name="token", length=5000)
	private String token;
	
	@Column
	private String insertDate;
}
