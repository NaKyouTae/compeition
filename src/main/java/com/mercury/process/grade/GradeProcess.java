package com.mercury.process.grade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.jpa.model.grade.Grade;
import com.mercury.jpa.repository.grade.GradeRepository;

@Component
@Transactional
@SuppressWarnings("unchecked")
public class GradeProcess {

	@Autowired
	private GradeRepository gradeRepository;

	public <T extends Object> T seGrades() throws Exception {
		return (T) gradeRepository.findAll(Sort.by(Sort.Direction.ASC, "gradeOrder"));
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
		gradeRepository.delete(grade);
		return (T) Boolean.TRUE;
	}
	
}
