package com.mercury.service.two;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.jpa.model.two.Two;
import com.mercury.jpa.model.word.Word;
import com.mercury.process.two.TwoProcess;
import com.mercury.service.love.LoveService;
import com.mercury.service.word.WordService;
import com.mercury.util.DateUtil;
import com.mercury.util.ObjectUtil;
import com.mercury.vo.five.FiveVO;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class TwoService {

	@Autowired
	private TwoProcess twoProcess;
	@Autowired
	private WordService weekWordService;
	@Autowired
	private LoveService loveService;

	public <T extends Object> T getTotalPoint(String userIdx) throws Exception {
		return (T) twoProcess.getTotalPoint(userIdx);
	}

	public <T extends Object> T getPopular(String userIdx) throws Exception {
		List<Two> list = twoProcess.getPopular();
		List<Two> result = checkLoveOfList(list, userIdx);
		return (T) result;
	}

	public <T extends Object> T getList(String userIdx) throws Exception {
		List<Two> list = twoProcess.getList();
		List<Two> result = checkLoveOfList(list, userIdx);
		return (T) result;
	}

	public <T extends Object> T seByUser(String userIdx) throws Exception {
		List<Two> list = twoProcess.seByUser(userIdx);
		List<Two> result = checkLoveOfList(list, userIdx);
		return (T) result;
	}

	public <T extends Object> T seByWord(String userIdx) throws Exception {
		List<Two> list = twoProcess.seByWord();
		List<Two> result = checkLoveOfList(list, userIdx);
		return (T) result;
	}

	public <T extends Object> T inTwo(Two two) throws Exception {
		Word word = weekWordService.getWeekWords("TWO");

		two.setIdx(UUID.randomUUID().toString().replace("-", ""));
		two.setInsertDate(DateUtil.now());
		two.setWordIdx(word.getIdx());
		two.setWord(word.getWord());

		return (T) twoProcess.inTwo(two);
	}

	public <T extends Object> T upTwo(Two two) throws Exception {
		two.setUpdateDate(DateUtil.now());
		return (T) twoProcess.upTwo(two);
	}

	public <T extends Object> T deTwo(Two two) throws Exception {
		return (T) twoProcess.deTwo(two);
	}

	public <T extends Object> T deTwoAllEntities(List<Two> twos)
			throws Exception {
		return (T) twoProcess.deTwoAllEntities(twos);
	}

	public <T extends Object> T checkLoveOfList(List<Two> list, String userIdx)
			throws Exception {
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
	}
}
