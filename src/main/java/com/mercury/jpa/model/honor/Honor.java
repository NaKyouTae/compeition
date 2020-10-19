package com.mercury.jpa.model.honor;

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
@Table(name = "MERCURY_TB_HONOR")
public class Honor implements Serializable {

	private static final long serialVersionUID = -4181079303817054859L;

	@Id
	@Column
	private String idx;
	
	@Column
	private String pidx;

	@Column
	private String userName;

	@Column
	private String contentIdx;
	
	@Column
	private String contentOne;
	
	@Column
	private String contentTwo;
	
	@Column
	private String contentThree;
	
	@Column
	private Integer point;
	
	@Column
	private String word;

	@Column
	private String createDate;
	
	@Column
	private String startDate;
	
	@Column
	private String endDate;
	
	@Column
	private Integer year;
	
	@Column
	private Integer month;
	
	@Column
	private Integer weeks;
	
}
