package com.competition.dto.menu;

import java.util.List;

import com.competition.jpa.model.menu.Menu;

import lombok.Data;

@Data
public class MenuDto {
	private String idx;
	private String parent;
	private String title;
	private String url;
	private String insert_date;
	private String group;
	private Integer order;
	private Integer level;
	private Boolean child;
	private List<Menu> children;
}