package com.mercury.process.notice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.jpa.model.notice.Notice;
import com.mercury.jpa.model.user.UserNotice;
import com.mercury.jpa.repository.notice.NoticeRepository;
import com.mercury.jpa.repository.user.UserNoticeRepository;

@Component
@Transactional
@SuppressWarnings("unchecked")
public class NoticeProcess {

	@Autowired
	private NoticeRepository noticeRepository;

	@Autowired
	private UserNoticeRepository userNoticeRepository;

	public <T extends Object> T neverOpen(UserNotice notice) throws Exception {
		return (T) userNoticeRepository.save(notice);
	}

	public <T extends Object> T seNoticePop(String type, String username)
			throws Exception {
		List<UserNotice> un = userNoticeRepository.findByUserName(username);
		List<Notice> notice = noticeRepository
				.findByType(type.equals("true") ? Boolean.TRUE : Boolean.FALSE);
		List<Notice> result = new ArrayList<Notice>();

		for (int i = 0; i < notice.size(); i++) {
			Notice n = notice.get(i);
			if (un.size() == 0) {
				result.add(n);
			} else {
				for (int j = 0; j < un.size(); j++) {
					UserNotice u = un.get(j);

					if (!n.getIdx().equals(u.getNoticeIdx())) {
						result.add(n);
					}

				}
			}
		}

		return (T) result;
	}

	public <T extends Object> T seNotices() throws Exception {
		return (T) noticeRepository
				.findAll(Sort.by(Sort.Direction.DESC, "type", "insertDate"));
	}

	public <T extends Object> T seNotice(String idx) throws Exception {
		return (T) noticeRepository.findByIdx(idx);
	}

	public <T extends Object> T inNotice(Notice notice) throws Exception {
		return (T) noticeRepository.save(notice);
	}

	public <T extends Object> T upNotice(Notice notice) throws Exception {
		return (T) noticeRepository.save(notice);
	}

	public <T extends Object> T deNotice(Notice notice) throws Exception {
		noticeRepository.delete(notice);
		return (T) Boolean.TRUE;
	}

}
