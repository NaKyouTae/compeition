package com.competition.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name ="MENUMAPPINGROLE")
public class MenuMappingRole implements Serializable {

	private static final long serialVersionUID = -2114602465412441179L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Long idx;
	
	@Column
	private String roleIdx;
	
	@Column
	private String roleTitle;

}
