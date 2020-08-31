package com.mercury.service.three;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercury.jpa.model.three.Three;
import com.mercury.jpa.model.word.Word;
import com.mercury.process.three.ThreeProcess;
import com.mercury.service.love.LoveService;
import com.mercury.service.word.WordService;
import com.mercury.util.DateUtil;
import com.mercury.util.ObjectUtil;
import com.mercury.vo.five.FiveVO;

@Service
@SuppressWarnings("unchecked")
public class ThreeService {

	@Autowired
	private ThreeProcess threeProcess;
	@Autowired
	private WordService weekWordService;
	@Autowired
	private LoveService loveService;

	public <T extends Object> T getTotalPoint(String userIdx) throws Exception {
		try {
			return (T) threeProcess.getTotalPoint(userIdx);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}

	public <T extends Object> T getPopular(String userIdx) throws Exception {
		try {
			List<Three> list = threeProcess.getPopular();
			List<Three> result = checkLoveOfList(list, userIdx);
			return (T) result;
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}

	public <T extends Object> T getList(String userIdx) throws Exception {
		try {
			List<Three> list = threeProcess.getList();
			List<Three> result = checkLoveOfList(list, userIdx);
			return (T) result;
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}

	public <T extends Object> T seByUser(String userIdx) throws Exception {
		try {
			List<Three> list = threeProcess.seByUser(userIdx);
			List<Three> result = checkLoveOfList(list, userIdx);
			return (T) result;
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}

	public <T extends Object> T seByWord(String userIdx) throws Exception {
		try {
			List<Three> list = threeProcess.seByWord();
			List<Three> result = checkLoveOfList(list, userIdx);
			return (T) result;
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}

	public <T extends Object> T seThreeByIdx(String idx) throws Exception {
		try {
			return (T) threeProcess.seThreeByIdx(idx);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}

	public <T extends Object> T inThree(Three three) throws Exception {
		try {
			Word word = weekWordService.getWeekWords("THREE");

			three.setIdx(UUID.randomUUID().toString().replace("-", ""));
			three.setInsertDate(DateUtil.now());
			three.setWordIdx(word.getIdx());
			three.setWord(word.getWord());

			return (T) threeProcess.inThree(three);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}

	public <T extends Object> T upThree(Three three) throws Exception {
		try {
			three.setUpdateDate(DateUtil.now());
			return (T) threeProcess.upThree(three);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}

	public <T extends Object> T deThree(Three three) throws Exception {
		try {
			threeProcess.deThree(three);
			return (T) Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}

	public <T extends Object> T deThreeAllEntities(List<Three> threes)
			throws Exception {
		try {
			return (T) threeProcess.deThreeAllEntities(threes);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T checkLoveOfList(List<Three> list, String userIdx) throws Exception {
		try {
			List<FiveVO> result = list.stream().map(item -> {
				FiveVO f = new FiveVO();
				try {
					ObjectUtil.toObj(item, f);
					f.setMe(loveService.checkLove(item.getIdx(), userIdx));
				} catch (Exception e) {
					e.printStackTrace();
				}
				return f;
			}).collect(Collectors.toList());
			
			return (T) result;
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
}
