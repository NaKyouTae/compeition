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
@Table(name = "MERCURY_TB_TOKEN_REFRESH")
public class TokenRefresh implements Serializable {

	private static final long serialVersionUID = 4368682883404823822L;

	@Id
	@Column
	private String userName;

	@Column(name="token", length=5000)
	private String token;
	
	@Column
	private String insertDate;
}
