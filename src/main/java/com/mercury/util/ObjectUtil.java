package com.mercury.util;

import java.lang.reflect.Field;

@SuppressWarnings("unchecked")
public class ObjectUtil {
	
	/**
	 * 
	 * en) Convert Source Object to Target Object.
	 * kr) Source Object를 Target Object로 변환한다.
	 * 
	 * 변수 명이 같고 Null이 아닌 변수가 Target Object의 변수로 입력된다.
	 * 
	 * @param <T> Target Object
	 * @param <S> Source Object
	 * @param source
	 * @param target
	 * @return Target Object Type new Instance
	 * @throws Exception
	 */
	public static <T, S extends Object> T toObj(S source, T target) throws Exception {
		try {
			Field[] sField = source.getClass().getDeclaredFields();
			Field[] tField = target.getClass().getDeclaredFields();
			
			
			for(Field s : sField) {
				for(Field t : tField) {
					if(s.getName().equals("serialVersionUID")) continue;
					if(s.getName().equals(t.getName())) {
						s.setAccessible(true);
						t.setAccessible(true);
						
						Object value = s.get(source);
						
						if(value != null) {
							t.set(target, value);
						}
					}
				}
			}
			return (T) target;
		} catch (Exception e) {
			 e.printStackTrace();
			 return (T) e;
		}
	}
} 
