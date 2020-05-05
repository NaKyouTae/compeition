package com.competition.process.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.competition.jpa.model.notice.Notice;
import com.competition.jpa.repository.notice.NoticeRepository;

@Component
@SuppressWarnings("unchecked")
public class NoticeProcess {

	@Autowired
	private NoticeRepository noticeRepository;

	public <T extends Object> T seNotices() throws Exception {
		try {
			return (T) noticeRepository.findAll();
		} catch (Exception e) {
			return (T) e;
		}
	}

	public <T extends Object> T seNotice(String idx) throws Exception {
		try {
			return (T) noticeRepository.findByIdx(idx);
		} catch (Exception e) {
			return (T) e;
		}
	}

	public <T extends Object> T inNotice(Notice notice) throws Exception {
		try {
			return (T) noticeRepository.save(notice);
		} catch (Exception e) {
			return (T) e;
		}
	}

	public <T extends Object> T upNotice(Notice notice) throws Exception {
		try {
			return (T) noticeRepository.save(notice);
		} catch (Exception e) {
			return (T) e;
		}
	}

	public <T extends Object> T deNotice(Notice notice) throws Exception {
		noticeRepository.delete(notice);
		try {
			return (T) Boolean.TRUE;
		} catch (Exception e) {
			return (T) e;
		}
	}

}
