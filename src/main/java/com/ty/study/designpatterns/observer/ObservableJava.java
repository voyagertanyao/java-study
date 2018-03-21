package com.ty.study.designpatterns.observer;

import java.util.Map;
import java.util.Observable;

public class ObservableJava extends Observable {
	private String name;
	private int age;
	
	public ObservableJava(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	public void modifyInfo(Map<String, Object> map) {
		this.name = (String)map.get("name");
		this.age = (Integer)map.get("age");
		setChanged();
		notifyObservers();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
