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

	@Id
	@Column
	public String idx;
	
	@Column
	public String title;
	
	@Column(name = "menu_group")
	public String menugroup;
	
	@Column
	@Nullable
	public String url;
	
	
	@Column(name = "menu_order")
	public Integer menuorder;
	
	
	@Column
	public Integer level;
	
	@Column
	public Boolean child;
	
	@Column(name = "insert_date")
	public String insertdate;
	
	@Column
	@Nullable
	public String parent = "null";

}
