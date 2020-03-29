package com.competition.vo.menu;

import java.util.List;

import lombok.Data;

@Data
public class MenuVO {
	private String idx;
	private String parent;
	private String title;
	private String url;
	private Integer level;
	private String insert_date;
	private String menu_group;
	private Integer menu_order;
	private Boolean child;
	private List<MenuVO> children;
}
