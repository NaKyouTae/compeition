package com.mercury.jpa.repository.mileage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mercury.jpa.model.mileage.Mileage;
@Repository
public interface MileageRepository extends JpaRepository<Mileage, Long>{
	Mileage findByIdx(String idx);
	List<Mileage> findByUserName(String userName);
}


