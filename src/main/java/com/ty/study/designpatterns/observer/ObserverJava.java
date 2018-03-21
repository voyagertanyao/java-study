package com.ty.study.designpatterns.observer;

import java.util.Observable;
import java.util.Observer;

public class ObserverJava implements Observer {
	private String name;
	
	public ObserverJava(String name) {
		this.name = name;
	}
	
	public void update(Observable o, Object arg) {
		if(o instanceof ObservableJava) {
			ObservableJava obj = (ObservableJava) o;
			this.getInfo(obj.getName(),obj.getAge());
		}
	}
	
	private void getInfo(String name, int age) {
		System.out.println(this.name + "说name:" + name + ";age:"+ age);
	}

}
