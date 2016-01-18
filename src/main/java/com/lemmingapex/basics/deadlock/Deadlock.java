package com.lemmingapex.basics.deadlock;

public class Deadlock implements Runnable {

	final int id;
	
	public Deadlock(int pId) {
		id = pId;
	}
	
	public void method1() {
		synchronized (String.class) {
			System.out.println("Acquired lock on String.class object");

			synchronized (Integer.class) {
				System.out.println("Acquired lock on Integer.class object");
			}
		}
	}

	public void method2() {
		synchronized (Integer.class) {
			System.out.println("Acquired lock on Integer.class object");

			synchronized (String.class) {
				System.out.println("Acquired lock on String.class object");
			}
		}
	}

	@Override
	public void run() {
		method1();
		method2();
		System.out.println(id + " is all done!");
	}
}
