package com.mercury.process.mileage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mercury.jpa.model.mileage.Mileage;
import com.mercury.jpa.repository.mileage.MileageRepository;

@Component
@SuppressWarnings("unchecked")
public class MileageProcess {
	
	@Autowired
	private MileageRepository mileageRepository;
	
	public <T extends Object> T seMileageByUserName(String userName) throws Exception {
		try {
			return (T) mileageRepository.findByUserName(userName);
		} catch (Exception e) {
			return (T) e;
		}
	}
	
	public <T extends Object> T seMileages() throws Exception {
		try {
			return (T) mileageRepository.findAll();
		} catch (Exception e) {
			return (T) e;
		}
	}
	
	public <T extends Object> T seMileage(String idx) throws Exception {
		try {
			return (T) mileageRepository.findByIdx(idx);
		} catch (Exception e) {
			return (T) e;
		}
	}
	
	public <T extends Object> T inMileage(Mileage mileage) throws Exception {
		try {
			return (T) mileageRepository.save(mileage);
		} catch (Exception e) {
			return (T) e;
		}
	}
	
	public <T extends Object> T upMileage(Mileage mileage) throws Exception {
		try {
			return (T) mileageRepository.save(mileage);
		} catch (Exception e) {
			return (T) e;
		}
	}
	
	public <T extends Object> T deMileage(Mileage mileage) throws Exception {
		try {
			mileageRepository.delete(mileage);
			return (T) Boolean.TRUE;
		} catch (Exception e) {
			return (T) e;
		}
	}
	
	public <T extends Object> T deMileageAllEntities(List<Mileage> mileage) throws Exception {
		try {
			mileageRepository.deleteAll(mileage);
			return (T) Boolean.TRUE;
		} catch (Exception e) {
			return (T) e;
		}
	}
}
