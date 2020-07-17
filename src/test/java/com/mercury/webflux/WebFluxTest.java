package com.mercury.webflux;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * 
 * 2020-03-20 CRUD Completed
 * 
 * @author nkt
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebFluxTest {

	@Test
	public void test() throws Exception {
		
		Flux.range(1, 10)
		.publishOn(Schedulers.newElastic("test-1"))
		.map(x -> {
			System.out.println(Thread.currentThread().getName());
			return x;
		})
		.publishOn(Schedulers.newElastic("test-2"))
		.map(x -> {
			System.out.println(Thread.currentThread().getName());
			return x;
		})
		.subscribe();
	}
}
