package com.competition.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.Nullable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name = "MENU")
public class Menu implements Serializable {

	private static final long serialVersionUID = -7965795983437805021L;

	@Column
	private String title;
	
	@Column(name = "menu_group")
	private String menugroup;
	
	@Column
	@Nullable
	private String url;
	
	
	@Column(name = "menu_order")
	private Integer menuorder;
	
	
	@Column
	private Integer level;
	
	@Column
	private Boolean child;
	
	@Column(name = "insert_date")
	private String insertdate;
	
	@Id
	@Column
	private String idx;
	
	@Column
	@Nullable
	private String parent = "null";

}
