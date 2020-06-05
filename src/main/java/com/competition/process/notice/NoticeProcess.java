package com.competition.process.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.competition.jpa.model.notice.Notice;
import com.competition.jpa.model.user.UserNotice;
import com.competition.jpa.repository.notice.NoticeRepository;
import com.competition.jpa.repository.user.UserNoticeRepository;

@Component
@SuppressWarnings("unchecked")
public class NoticeProcess {

	@Autowired
	private NoticeRepository noticeRepository;
	
	@Autowired
	private UserNoticeRepository userNoticeRepository;
	
	public <T extends Object> T neverOpen(UserNotice notice) throws Exception {
		try {
			return (T) userNoticeRepository.save(notice);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seNoticePop(String type, String username) throws Exception {
		try {
			
			List<UserNotice> un = userNoticeRepository.findByUserName(username);
			List<Notice> notice = noticeRepository.findByType(type);
			
			return (T) notice; 
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
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
