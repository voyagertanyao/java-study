package com.ty.study.thread;

public class ThreadSource {
	private boolean flag = true;
	
	public ThreadSource(boolean flag) {
		this.flag = flag;
	}
	
	public boolean getFlag() {
		return this.flag;
	}
	
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}
