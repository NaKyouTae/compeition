package com.competition.jpa.model.three;

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
@Table(name = "THREE")
public class Three implements Serializable {

	private static final long serialVersionUID = -8570162283978145091L;

	@Id
	@Column
	private String idx;
	
	@Column
	private String wordIdx;

	@Column
	private String contentOne;
	
	@Column
	private String contentTwo;
	
	@Column
	private String contentThree;

	@Column
	private String userName;
	@Column
	private String userIdx;

	@Column
	private String insertDate;
	
	@Column
	private String updateDate;

	@Column
	private Integer point = 0;
	
	@Column
	private boolean love = false;
	@Column
	private String loveName;

}
