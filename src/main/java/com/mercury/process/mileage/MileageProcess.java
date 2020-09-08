package com.mercury.process.mileage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.jpa.model.mileage.Mileage;
import com.mercury.jpa.repository.mileage.MileageRepository;

@Component
@Transactional
@SuppressWarnings("unchecked")
public class MileageProcess {

	@Autowired
	private MileageRepository mileageRepository;

	public <T extends Object> T seMileageByUserName(String userName) throws Exception {
		return (T) mileageRepository.findByUserName(userName);
	}

	public <T extends Object> T seMileages() throws Exception {
		return (T) mileageRepository.findAll();
	}

	public <T extends Object> T seMileage(String idx) throws Exception {
		return (T) mileageRepository.findByIdx(idx);
	}

	public <T extends Object> T inMileage(Mileage mileage) throws Exception {
		return (T) mileageRepository.save(mileage);
	}

	public <T extends Object> T upMileage(Mileage mileage) throws Exception {
		return (T) mileageRepository.save(mileage);
	}

	public <T extends Object> T deMileage(Mileage mileage) throws Exception {
		mileageRepository.delete(mileage);
		return (T) Boolean.TRUE;
	}

	public <T extends Object> T deMileageAllEntities(List<Mileage> mileage) throws Exception {
		mileageRepository.deleteAll(mileage);
		return (T) Boolean.TRUE;
	}
}
