package com.mercury.service.honor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.jpa.model.honor.Honor;
import com.mercury.process.honor.HonorProcess;
import com.mercury.vo.honor.HonorVO;
import com.mercury.vo.honor.HonorVO.HonorWeek;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class HonorService {
	@Autowired
	private HonorProcess honorProcess;

	public <T extends Object> T seHonors() throws Exception {
		List<Honor> honorListPidxNull = seHonorByPidxIsNull();
		
		List<HonorWeek> rootList = new ArrayList<>();
		
		HonorVO vo = new HonorVO();

		for (int i = 0; i < honorListPidxNull.size(); i++) {
			HonorWeek rootVo = new HonorWeek();
			Honor honorVo = honorListPidxNull.get(i);

			rootVo.setIdx(honorVo.getIdx());
			rootVo.setYear(honorVo.getYear());
			rootVo.setMonth(honorVo.getMonth());
			rootVo.setWeeks(honorVo.getWeeks());
			
			List<Object> honors = seHonorByPidx(honorVo.getIdx());

			rootVo.setDatas(honors);
			
			rootList.add(rootVo);
		}

		vo.setRoot(rootList);
		
		return (T) vo;
	}
	
	public <T extends Object> T seHonorByYearAndMonth(Integer year, Integer month) throws Exception {
		List<Honor> honorListPidxNull = seHonorByPidxIsNullAndYearAndMonth(year, month);
		
		List<HonorWeek> rootList = new ArrayList<>();
		
		HonorVO vo = new HonorVO();

		for (int i = 0; i < honorListPidxNull.size(); i++) {
			HonorWeek rootVo = new HonorWeek();
			Honor honorVo = honorListPidxNull.get(i);

			rootVo.setIdx(honorVo.getIdx());
			rootVo.setYear(honorVo.getYear());
			rootVo.setMonth(honorVo.getMonth());
			rootVo.setWeeks(honorVo.getWeeks());
			
			List<Honor> honors = seHonorByPidx(honorVo.getIdx());
			
			List<Honor> threeList = new ArrayList<>();
			List<Honor> twoList = new ArrayList<>();
			
			
			for(Honor h : honors) {
				if(h.getWord().length() > 2) {					
					threeList.add(h);
				}else {
					twoList.add(h);
				}
			}
			
			List<Object> dataList = new ArrayList<>();
			dataList.add(threeList);
			dataList.add(twoList);
			rootVo.setDatas(dataList);
			
			rootList.add(rootVo);
		}

		vo.setRoot(rootList);
		
		return (T) vo;
	}

	public <T extends Object> T seHonorByIdx(String idx) throws Exception {
		return (T) honorProcess.seHonorByIdx(idx);
	}

	public <T extends Object> T seHonorByUserName(String username)
			throws Exception {
		return (T) honorProcess.seHonorByUserName(username);
	}

	public <T extends Object> T seHonorByContentIdx(String contentIdx)
			throws Exception {
		return (T) honorProcess.seHonorByContentIdx(contentIdx);
	}

	public <T extends Object> T seHonorByWord(String word) throws Exception {
		return (T) honorProcess.seHonorByWord(word);
	}
	
	public <T extends Object> T seHonorByPidxIsNull() throws Exception {
		return (T) honorProcess.seHonorByPidxIsNull();
	}
	
	public <T extends Object> T seHonorByPidx(String pidx) throws Exception {
		return (T) honorProcess.seHonorByPidx(pidx);
	}
	
	public <T extends Object> T seHonorByPidxIsNullAndYearAndMonth(Integer year, Integer month) throws Exception {
		return (T) honorProcess.seHonorByPidxIsNullAndYearAndMonth(year, month);
	}
}
