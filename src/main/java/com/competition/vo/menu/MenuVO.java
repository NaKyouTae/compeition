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
	private String insertdate;
	private String menugroup;
	private Integer menuorder;
	private Boolean child;
	private List<MenuVO> children;
}
