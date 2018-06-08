package com.ty.study.thread;

public class BankTest {
	
	public static void main(String[] args) {
		
		Bank bank = new Bank();
		for(int i=0; i<3; i++) {
			Runnable r = () -> {
				while(true) {
					bank.readA();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			new Thread(r).start();
		}
		for(int i=0; i<2; i++) {
			Runnable r = () -> {
				while(true) {
					bank.writeA();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			};
			new Thread(r).start();
		}
	}
}
