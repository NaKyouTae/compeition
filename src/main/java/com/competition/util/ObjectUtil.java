package com.competition.util;

import java.lang.reflect.Field;

@SuppressWarnings("unchecked")
public class ObjectUtil {
	
	/**
	 * 
	 * en) Convert Source Object to Target Object.
	 * kr) Source Object를 Target Object로 변환한다.
	 * 
	 * 변수 명이 같아야 들어간다.
	 * 
	 * @param <T> Target Object
	 * @param <S> Source Object
	 * @param source
	 * @param target
	 * @return Target Object Type
	 * @throws Exception
	 */
	public static <T, S extends Object> T toObject(S source, T target) throws Exception {
		try {
			T tObj = (T) target.getClass().getDeclaredConstructor().newInstance();
			
			Field[] sField = source.getClass().getDeclaredFields();
			Field[] tField = target.getClass().getDeclaredFields();
			
			
			for(Field s : sField) {
				for(Field t : tField) {
					if(s.getName().equals("serialVersionUID")) continue;
					if(s.getName().equals(t.getName())) {
						s.setAccessible(true);
						t.setAccessible(true);
						
						Object value = s.get(source);
						
						t.set(tObj, value);
					}
				}
			}
			return (T) tObj;
		} catch (Exception e) {
			 e.printStackTrace();
			 return (T) e;
		}
	}
	
}
