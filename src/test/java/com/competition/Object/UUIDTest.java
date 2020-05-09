package com.competition.Object;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UUIDTest {
	
	@Test
	void test() throws Exception {
		
		
		System.out.println(UUID.randomUUID().toString().replace("-", ""));
	}
}
