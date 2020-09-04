package com.mercury.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("unchecked")
public class ApplicationContextProvider implements ApplicationContextAware {

	private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    	context = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return context;
    }
    
	public static <T extends Object> T getBean(String beanName) {
    	return (T) context.getBean(beanName);
    }

}
