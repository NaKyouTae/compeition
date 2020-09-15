package com.mercury.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;

public class LoggingUtil {
	
//	Logger log = LoggerFactory.getLogger(LogTests.class);
//	LoggingUtil.log(log, "Error Params = [{}]", map, e);
	
	public static void log(Logger logger, String formmat, HashMap<String, Object> map, Throwable thr) {
		Set<String> key = map.keySet();
		Iterator<String> it = key.iterator();
		
		String result = "";
		String comma = ", ";
		String equals = "=";
		
		while(it.hasNext()) {
			String k = it.next();
			
			if(!it.hasNext()) comma = "";
			
			if(Map.class.isInstance(map.get(k))) {
				result += k + equals + map.get(k).toString() + comma;
			}else {				
				result += k + equals + map.get(k) + comma;
			}
			
		}
		
		logger.error(formmat, result, thr);
	} 
}
