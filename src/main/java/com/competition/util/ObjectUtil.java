package com.competition.util;

import java.lang.reflect.Field;

public class ObjectUtil {
	
	/**
	 * 
	 * en) Convert Source Object to Target Object.
	 * kr) Source Object를 Target Object로 변환한다.
	 * 
	 * @param <T> Target Object
	 * @param <S> Source Object
	 * @param source
	 * @param target
	 * @return Target Object Type
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T, S extends Object> T toObject(S source, T target) throws Exception {
		Class<S> sClass = (Class<S>) source.getClass();
		
		T tObj = (T) target.getClass().getDeclaredConstructor().newInstance();
		
		Field[] sField = source.getClass().getDeclaredFields();
		Field[] tField = target.getClass().getDeclaredFields();
		
		
		for(Field s : sField) {
			for(Field t : tField) {
				if(s.getName().equals(t.getName())) {
					Class<?> tClazz = tObj.getClass();
					Field tOne = tClazz.getDeclaredField(t.getName());
					
					Field sOne = sClass.getDeclaredField(s.getName());
					
					Object value = sOne.get(source);
					
					tOne.set(tObj, value);
				}
			}
		}
		return (T) tObj;
	}
}
