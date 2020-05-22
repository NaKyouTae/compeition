package com.competition.process.grade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.competition.jpa.model.grade.Grade;
import com.competition.jpa.repository.grade.GradeRepository;

@Component
@SuppressWarnings("unchecked")
public class GradeProcess {

	@Autowired
	private GradeRepository gradeRepository;

	public <T extends Object> T seGrades() throws Exception {
		return (T) gradeRepository.findAll();
	}
	
	public <T extends Object> T seGrade(String idx) throws Exception {
		return (T) gradeRepository.findByIdx(idx);
	}
	
	public <T extends Object> T inGrade(Grade grade) throws Exception {
		return (T) gradeRepository.save(grade);
	}
	public <T extends Object> T upGrade(Grade grade) throws Exception {
		return (T) gradeRepository.save(grade);
	}

	public <T extends Object> T deGrade(Grade grade) throws Exception {
		try {
			gradeRepository.delete(grade);
			
			return (T) Boolean.TRUE;
		} catch (Exception e) {
			return (T) e;
		}
	}
	
}
