package com.competition.service.grade;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.competition.jpa.model.grade.Grade;
import com.competition.process.grade.GradeProcess;
import com.competition.util.DateUtil;

@Service
@SuppressWarnings("unchecked")
public class GradeService {
	
	@Autowired
	private GradeProcess gradeProcess;
	
	public <T extends Object> T seGrades() throws Exception {
		try {
			return (T) gradeProcess.seGrades();
		}catch(Exception e) {
			return (T) e;
		}
	}
	
	public <T extends Object> T seGrade(String idx) throws Exception {
		try {
			return (T) gradeProcess.seGrade(idx);
		}catch(Exception e) {
			return (T) e;
		}
	}
	
	public <T extends Object> T inGrade(Grade grade) throws Exception {
		try {
			grade.setIdx(UUID.randomUUID().toString().replace("-", ""));
			grade.setInsertDate(DateUtil.now());
			return (T) gradeProcess.inGrade(grade);
		}catch(Exception e) {
			return (T) e;
		}
	}
	
	public <T extends Object> T upGrade(Grade grade) throws Exception {
		try {
			return (T) gradeProcess.upGrade(grade);
		}catch(Exception e) {
			return (T) e;
		}
	}
	
	public <T extends Object> T deGrade(Grade grade) throws Exception{
		try {
			return (T) gradeProcess.deGrade(grade);
		}catch(Exception e) {
			return (T) e;
		}
		
	}
}
