package com.mercury.vo.five;

import lombok.Data;

@Data
public class FiveVO {
	private String idx;
	private String word;
	private String wordIdx;
	private String contentOne;
	private String contentTwo;
	private String userName;
	private String userIdx;
	private String insertDate;
	private String updateDate;
	private Integer point = 0;
	private boolean love = false;
	private String loveName;
	private Boolean me;
}
