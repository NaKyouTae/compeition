package com.mercury.service.honor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercury.jpa.model.honor.Honor;
import com.mercury.process.honor.HonorProcess;
import com.mercury.vo.honor.HonorVO;
import com.mercury.vo.honor.HonorVO.HonorWeek;

@Service
@SuppressWarnings("unchecked")
public class HonorService {
	@Autowired
	private HonorProcess honorProcess;
	
	public <T extends Object> T seHonors() throws Exception{
		try {
			
			List<Honor> honor_list = honorProcess.seHonors();
			
			System.out.println(honor_list.toString());
			HonorVO vo = new HonorVO();
			List<HonorWeek> week = new ArrayList<>();		
			HonorWeek hw = new HonorWeek();
			
			List<Honor> list = new ArrayList<>();
			for(int i = 0; i<honor_list.size(); i++) {
				Honor honor = honor_list.get(i);
				
				
				if(i != 0) {					
					if(!honor.getWord().equals(honor_list.get(i-1).getWord())) {
						
						hw.setStartDate(honor_list.get(i-1).getStartDate());
						hw.setEndDate(honor_list.get(i-1).getEndDate());
						hw.setWord(honor_list.get(i-1).getWord());
						hw.setDatas(list);
						
						week.add(hw);
						
						hw = new HonorWeek();
						list = new ArrayList<>();
						
						list.add(honor);
					}
					
					if(honor_list.size() == i+1) {
						hw.setStartDate(honor_list.get(i).getStartDate());
						hw.setEndDate(honor_list.get(i).getEndDate());
						hw.setWord(honor_list.get(i).getWord());
						hw.setDatas(list);
						
						week.add(hw);						
						list.add(honor);
					}
					
					
					list.add(honor);
				}else {
					list.add(honor);					
				}
				
			}
			
			vo.setHonors(week);
			
			return (T) vo;
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seHonorByIdx(String idx) throws Exception{
		try {
			return (T) honorProcess.seHonorByIdx(idx);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seHonorByUserName(String username) throws Exception{
		try {
			return (T) honorProcess.seHonorByUserName(username);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seHonorByContentIdx(String contentIdx) throws Exception{
		try {
			return (T) honorProcess.seHonorByContentIdx(contentIdx);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seHonorByWord(String word) throws Exception{
		try {
			return (T) honorProcess.seHonorByWord(word);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
}
