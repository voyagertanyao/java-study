package com.ty.study.thread;

public class ThreadException  extends Thread{
	
	volatile ThreadSource src = null;
	
	public ThreadException(ThreadSource src) {
		this.src = src;
	}
	@Override
	public void run() {
		System.out.println("进入run()");
		while(src.getFlag()) {
			System.out.println("-oo-");
		}
		System.out.println("结束run()");
	}
	
	public static void main(String[] args) {
		ThreadSource src = new ThreadSource(true);
		new ThreadException(src).start();
		src = new ThreadSource(false);
	}

}
