package com.mercury.service.word;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.jpa.model.word.Word;
import com.mercury.process.word.WordProcess;
import com.mercury.util.DateUtil;
import com.mercury.util.ObjectUtil;
import com.mercury.vo.word.WordVO;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class WordService {

	@Autowired
	private WordProcess weekWordProcess;

	public <T extends Object> T getLists() throws Exception {
		return (T) weekWordProcess.getLists();
	}

	public <T extends Object> T getWeekWords(String group) throws Exception {
		return (T) weekWordProcess.getWeekWords(group);
	}
	public <T extends Object> T seWord(String wordIdx) throws Exception {
		return (T) weekWordProcess.seWord(wordIdx);
	}

	public <T extends Object> T inWord(WordVO vo) throws Exception {
		Word word = ObjectUtil.toObj(vo, new Word());

		word.setIdx(UUID.randomUUID().toString().replace("-", ""));
		word.setInsertDate(DateUtil.now());

		return (T) weekWordProcess.inWord(word);
	}
	public <T extends Object> T upWord(WordVO vo) throws Exception {
		Word word = ObjectUtil.toObj(vo, new Word());

		return (T) weekWordProcess.upWord(word);
	}
	public <T extends Object> T deWord(WordVO vo) throws Exception {
		Word word = ObjectUtil.toObj(vo, new Word());

		weekWordProcess.deWord(word);
		return (T) Boolean.TRUE;
	}
}
