package com.mercury.service.notice;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.jpa.model.notice.Notice;
import com.mercury.jpa.model.user.User;
import com.mercury.jpa.model.user.UserNotice;
import com.mercury.process.notice.NoticeProcess;
import com.mercury.util.DateUtil;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class NoticeService {

	@Autowired
	private NoticeProcess noticeProcess;

	public <T extends Object> T neverOpen(Notice notice, User user)
			throws Exception {
		if (notice.getIdx() == null || notice.getTitle() == null
				|| user.getIdx() == null || user.getUsername() == null)
			return (T) Boolean.FALSE;

		UserNotice un = new UserNotice();

		un.setIdx(UUID.randomUUID().toString().replace("-", ""));
		un.setNoticeIdx(notice.getIdx());
		un.setNoticeTitle(notice.getTitle());
		un.setUserIdx(user.getIdx());
		un.setUserName(user.getUsername());

		return (T) noticeProcess.neverOpen(un);
	}

	public <T extends Object> T seNoticePop(String type, String username)
			throws Exception {
		return (T) noticeProcess.seNoticePop(type, username);
	}

	public <T extends Object> T seNotices() throws Exception {
		return (T) noticeProcess.seNotices();
	}

	public <T extends Object> T seNotice(String idx) throws Exception {
		return (T) noticeProcess.seNotice(idx);
	}

	public <T extends Object> T inNotice(Notice notice) throws Exception {
		notice.setIdx(UUID.randomUUID().toString().replace("-", ""));
		notice.setInsertDate(DateUtil.now());

		return (T) noticeProcess.inNotice(notice);
	}

	public <T extends Object> T upNotice(Notice notice) throws Exception {
		return (T) noticeProcess.upNotice(notice);
	}

	public <T extends Object> T deNotice(Notice notice) throws Exception {
		return (T) noticeProcess.deNotice(notice);
	}

}
