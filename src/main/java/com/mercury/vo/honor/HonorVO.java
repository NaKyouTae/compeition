package com.mercury.vo.honor;

import java.util.List;

import lombok.Data;

@Data
public class HonorVO {
	
	private List<HonorWeek> root;
	
	@Data
	public static class HonorWeek {	
		private String idx;
		private Integer year;
		private Integer month;
		private Integer weeks;
		private List<Object> datas;
	}
}
