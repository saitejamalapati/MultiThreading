package com.accolite.au;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumer {

	Random number = new Random();
	@SuppressWarnings("rawtypes")
	BlockingQueue queue = new ArrayBlockingQueue(20);

	@SuppressWarnings("unchecked")
	void producer() throws InterruptedException {
		int temp = number.nextInt(50);
		System.out.println("Produced: " + temp);
		queue.put(temp);
	}
	void consumer() throws InterruptedException {
		System.out.println("consumed: " + 	queue.take() );
	}


	public static void main(String[] args) {
		ProducerConsumer pc = new ProducerConsumer();
		Thread produce = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					int count = 0;
					while(count < 20) {
						pc.producer();
						count++;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}) ;
		Thread consumer = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					int count = 0;
					while(count < 20) {
						pc.consumer();
						count++;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}) ;
		produce.start();
		consumer.start();

	}
}
