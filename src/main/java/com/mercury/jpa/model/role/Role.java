package com.mercury.jpa.model.role;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "MERCURY_TB_ROLE")
public class Role implements Serializable {

	private static final long serialVersionUID = 1122861054404748086L;

	@Id
	@Column
	private String idx;
	
	@Column
	private String roleName;
	
	@Column
	private String insertDate;

	@Column
	private String changeDate;

}
