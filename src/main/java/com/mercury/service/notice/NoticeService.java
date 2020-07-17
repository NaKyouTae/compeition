package com.mercury.service.notice;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercury.jpa.model.notice.Notice;
import com.mercury.jpa.model.user.User;
import com.mercury.jpa.model.user.UserNotice;
import com.mercury.process.notice.NoticeProcess;
import com.mercury.util.DateUtil;

@Service
@SuppressWarnings("unchecked")
public class NoticeService {

	@Autowired
	private NoticeProcess noticeProcess;

	public <T extends Object> T neverOpen(Notice notice, User user) throws Exception {
		try {
			
			if(notice.getIdx() == null || notice.getTitle() == null || user.getIdx() == null || user.getUsername() == null) return (T) Boolean.FALSE;
			
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
