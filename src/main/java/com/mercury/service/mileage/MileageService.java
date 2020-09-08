package com.mercury.service.mileage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.jpa.model.mileage.Mileage;
import com.mercury.process.mileage.MileageProcess;
import com.mercury.util.DateUtil;
import com.mercury.util.UUIDUtil;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class MileageService {

	@Autowired
	private MileageProcess mileageProcess;

	public <T extends Object> T seMileageByUserName(String userName)
			throws Exception {
		return mileageProcess.seMileageByUserName(userName);
	}

	public <T extends Object> T seMileages() throws Exception {
		return mileageProcess.seMileages();
	}

	public <T extends Object> T seMileage(String idx) throws Exception {
		return mileageProcess.seMileage(idx);
	}

	public <T extends Object> T inMileage(Mileage mileage) throws Exception {
		mileage.setIdx(UUIDUtil.randomString());
		mileage.setPaymentDate(DateUtil.now());
		return mileageProcess.inMileage(mileage);
	}

	public <T extends Object> T upMileage(Mileage mileage) throws Exception {
		return mileageProcess.upMileage(mileage);
	}

	public <T extends Object> T deMileage(Mileage mileage) throws Exception {
		return mileageProcess.deMileage(mileage);
	}

	public <T extends Object> T deMileageAllEntities(List<Mileage> mileage)
			throws Exception {
		return mileageProcess.deMileageAllEntities(mileage);
	}
}
