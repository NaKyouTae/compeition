package com.mercury.service.grade;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.jpa.model.grade.Grade;
import com.mercury.process.grade.GradeProcess;
import com.mercury.util.DateUtil;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class GradeService {

	@Autowired
	private GradeProcess gradeProcess;

	public <T extends Object> T seGrades() throws Exception {
		return (T) gradeProcess.seGrades();
	}

	public <T extends Object> T seGrade(String idx) throws Exception {
		return (T) gradeProcess.seGrade(idx);
	}

	public <T extends Object> T inGrade(Grade grade) throws Exception {
		grade.setIdx(UUID.randomUUID().toString().replace("-", ""));
		grade.setInsertDate(DateUtil.now());
		return (T) gradeProcess.inGrade(grade);
	}

	public <T extends Object> T upGrade(Grade grade) throws Exception {
		return (T) gradeProcess.upGrade(grade);
	}

	public <T extends Object> T deGrade(Grade grade) throws Exception {
		return (T) gradeProcess.deGrade(grade);
	}
}
