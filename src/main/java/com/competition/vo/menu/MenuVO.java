package com.competition.vo.menu;

import lombok.Data;

@Data
public class MenuVO {
	private String idx;
	private String parent;
	private String title;
	private String url;
	private Integer order;
	private Integer level;
	private String insert_date;
}
