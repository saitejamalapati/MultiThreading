package com.accolite.au;

public class EvenOdd implements Runnable {

	private static int number = 0;

	EvenOdd(int num){
		super();
		number = num;
	}

	public static void nextValue(){
		number++;
	}

	synchronized void printEven() throws InterruptedException {
		System.out.println("Even: "+ number);
		nextValue();
		notifyAll();
	}

	synchronized void printOdd() throws InterruptedException {
		System.out.println("Odd: "+ number);
		nextValue();
		notifyAll();
	}

	@Override
	synchronized public void  run() {
		for(int i = 0;i < 100;i++) {
			try {
				if(Thread.currentThread().getName().equals("Odd")) {
					printOdd();
					wait();
				}
				else {
					printEven();
					wait();
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		EvenOdd obj=new EvenOdd(0);
		try {
			Thread even = new Thread(obj , "Even");
			Thread odd = new Thread(obj , "Odd");
			even.start();
			odd.start();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

