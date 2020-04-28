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
	private String insertDate;
	private String menuGroup;
	private Integer menuOrder;
	private Boolean child;
	private String roleIdx;
	private String roleTitle;
	private List<MenuVO> children;
}
