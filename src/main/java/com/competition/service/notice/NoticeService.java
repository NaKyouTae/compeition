package com.competition.service.notice;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.competition.jpa.model.notice.Notice;
import com.competition.jpa.model.user.User;
import com.competition.jpa.model.user.UserNotice;
import com.competition.process.notice.NoticeProcess;
import com.competition.util.DateUtil;

@Service
@SuppressWarnings("unchecked")
public class NoticeService {

	@Autowired
	private NoticeProcess noticeProcess;

	public <T extends Object> T neverOpen(Notice notice, User user) throws Exception {
		try {
			UserNotice un = new UserNotice();
			
			un.setIdx(UUID.randomUUID().toString().replace("-", ""));
			un.setNoticeIdx(notice.getIdx());
			un.setNoticeTitle(notice.getTitle());
			un.setUserIdx(user.getIdx());
			un.setUserName(user.getUsername());
			
			return (T) noticeProcess.neverOpen(un);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seNoticePop(String type, String username) throws Exception {
		try {
			return (T) noticeProcess.seNoticePop(type, username);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seNotices() throws Exception {
		try {
			return (T) noticeProcess.seNotices();
		} catch (Exception e) {
			return (T) e;
		}
	}

	public <T extends Object> T seNotice(String idx) throws Exception {
		try {
			return (T) noticeProcess.seNotice(idx);
		} catch (Exception e) {
			return (T) e;
		}
	}

	public <T extends Object> T inNotice(Notice notice) throws Exception {
		try {
			notice.setIdx(UUID.randomUUID().toString().replace("-", ""));
			notice.setInsertDate(DateUtil.now());

			return (T) noticeProcess.inNotice(notice);
		} catch (Exception e) {
			return (T) e;
		}
	}

	public <T extends Object> T upNotice(Notice notice) throws Exception {
		try {
			return (T) noticeProcess.upNotice(notice);
		} catch (Exception e) {
			return (T) e;
		}
	}

	public <T extends Object> T deNotice(Notice notice) throws Exception {
		try {
			return (T) noticeProcess.deNotice(notice);
		} catch (Exception e) {
			return (T) e;
		}
	}

}
