package com.mercury.service.mileage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercury.jpa.model.mileage.Mileage;
import com.mercury.process.mileage.MileageProcess;
import com.mercury.util.DateUtil;
import com.mercury.util.UUIDUtil;

@Service
@SuppressWarnings("unchecked")
public class MileageService {
	
	@Autowired
	private MileageProcess mileageProcess;
	
	public <T extends Object> T seMileageByUserName(String userName) throws Exception {
		try {
			return mileageProcess.seMileageByUserName(userName);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seMileages() throws Exception {
		try {
			return mileageProcess.seMileages();
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seMileage(String idx) throws Exception {
		try {
			return mileageProcess.seMileage(idx);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T inMileage(Mileage mileage) throws Exception {
		try {
			mileage.setIdx(UUIDUtil.randomString());
			mileage.setPaymentDate(DateUtil.now());
			return mileageProcess.inMileage(mileage);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T upMileage(Mileage mileage) throws Exception {
		try {
			return mileageProcess.upMileage(mileage);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T deMileage(Mileage mileage) throws Exception {
		try {
			return mileageProcess.deMileage(mileage);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T deMileageAllEntities(List<Mileage> mileage) throws Exception {
		try {
			return mileageProcess.deMileageAllEntities(mileage);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
}
