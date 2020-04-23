package com.competition.vo.menu;

import java.util.List;

import lombok.Data;

@Data
public class MenuVO {
	public String idx;
	public String parent;
	public String title;
	public String url;
	public Integer level;
	public String insertdate;
	public String menugroup;
	public Integer menuorder;
	public Boolean child;
	public List<MenuVO> children;
}
