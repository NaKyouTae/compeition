package com.mercury.dto.menu;

import java.util.List;

import com.mercury.jpa.model.menu.Menu;

import lombok.Data;

@Data
public class MenuDto {
	private String idx;
	private String parent;
	private String title;
	private String url;
	private String insertDate;
	private String group;
	private Integer order;
	private Integer level;
	private Boolean child;
	private String roleIdx;
	private String roleTitle;
	private List<Menu> children;
}