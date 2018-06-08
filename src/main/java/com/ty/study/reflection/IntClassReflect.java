package com.ty.study.reflection;

import java.lang.reflect.Field;

public class IntClassReflect {
	public static void main(String[] args) {
		Class intClass = IntClass.class;
		Field[] fields = intClass.getDeclaredFields();
		for(Field field : fields) {
			field.setAccessible(true);
			
			System.out.println(field.getName() + ":" + field.getType().getSimpleName() + "   --" + field.getGenericType().getTypeName());
		}
	}
}
