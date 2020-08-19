package com.mercury.vo.honor;

import java.util.List;

import com.mercury.jpa.model.honor.Honor;

import lombok.Data;

@Data
public class HonorVO {
	
	private List<HonorWeek> honors;
	
	@Data
	public static class HonorWeek {	
		private List<Honor> datas;
		private String word;
		private String startDate;
		private String endDate;
	}
}
