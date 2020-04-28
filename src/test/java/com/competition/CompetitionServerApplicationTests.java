package com.competition;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.competition.jpa.model.love.Love;

@SpringBootTest
class CompetitionServerApplicationTests {

	@Test
	void contextLoads() {
		
		List<Love> list = new ArrayList<>();
		
		Love loveO = new Love();

		loveO.setContentIdx("111");
		loveO.setIdx("test");
		loveO.setInsertDate("2020-04-23 09:54:00");
		loveO.setUserIdx("test");
		
		Love loveT = new Love();
		
		loveT.setContentIdx("111");
		loveT.setIdx("aaaaaaaa");
		loveT.setInsertDate("2020-04-23 09:54:00");
		loveT.setUserIdx("test");
		
		list.add(loveO);
		list.add(loveT);
		
		
		Boolean result = list.stream().anyMatch(i -> !"test".equals(i.getIdx()));
		System.out.println(result);
		
	}

}
