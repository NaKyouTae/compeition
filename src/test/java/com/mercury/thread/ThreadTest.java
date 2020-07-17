package com.mercury.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ThreadTest {
	
	@Autowired
	private ExecutorService excutor;
	
	public class TestThreadImple implements Runnable {
		private Integer count;
		
		public TestThreadImple(int count) {
			this.count = count;
		}
		@Override
		public void run() {
			Thread.currentThread().setName("Thread " + this.count);
			System.out.println(Thread.currentThread().getName());
			System.out.println("Thread "+ this.count +" END");
		}
	}
	
	public class TestThreadExtend extends Thread {
		@Override
		public void run() {
			Thread.currentThread().setName("Thread 2");
			System.out.println(Thread.currentThread().getName());
			System.out.println("Thread 2 END");
		}
	}
	
	
	@Test
	void test() {
		// Runnable
//		Runnable run = new TestThreadImple();
//		Thread th1 = new Thread(run);
		Thread th1 = new Thread(new TestThreadImple(1));
		
		// Thread
		Thread th2 = new TestThreadExtend();
		
		// Lamda
		Thread th3 = new Thread(() -> {
			Thread.currentThread().setName("Thread 3");
			System.out.println(Thread.currentThread().getName());
			System.out.println("Thread 3 END");
		});
		
		th1.start();
		th2.start();
		th3.start();
		
		
		this.excutor = Executors.newFixedThreadPool(2);
		
		excutor.submit(new TestThreadImple(4));
		
	}
	
}
