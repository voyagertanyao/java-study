package com.ty.study.designpatterns.observer;

import java.util.HashMap;
import java.util.Map;

public class ObserverTest {

	public static void main(String[] args) {
		
		ObservableJava observable = new ObservableJava("tanyao", 26);
		
		observable.addObserver(new ObserverJava("A"));
		observable.addObserver(new ObserverJava("B"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "yuanyuan");
		map.put("age", 31);
		observable.modifyInfo(map);
		System.out.println();
	}

}
