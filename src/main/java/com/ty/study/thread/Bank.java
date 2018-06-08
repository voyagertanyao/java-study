package com.ty.study.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class Bank {
    private volatile int a = 0;
    private Lock lock = new ReentrantLock();
    private Condition read = lock.newCondition();
	public void readA() {
		lock.lock();
		System.out.println(Thread.currentThread().getName()+"readA");
		try {
			while(a < 10) {
				try {
					read.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread().getName()+" read a="+a);
		}finally {
			lock.unlock();
		}
	}
	
	public void writeA() {
		if(lock.tryLock()) {
			try {
				a++;
				a++;
				System.out.println(Thread.currentThread().getName()+" write a="+a);
				if(a >10) {
					read.signalAll();
				}
			}finally {
				lock.unlock();
			}
		}
	}
	

}
