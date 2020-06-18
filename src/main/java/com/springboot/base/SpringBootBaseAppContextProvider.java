package com.springboot.base;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringBootBaseAppContextProvider implements ApplicationContextAware {
	

	private static ApplicationContext springBootBaseAppContext;

	@Override
	public void setApplicationContext(ApplicationContext springBootBaseAppContext) throws BeansException {
		SpringBootBaseAppContextProvider.springBootBaseAppContext = springBootBaseAppContext;

	}

	public static ApplicationContext getApplicationContext() {
		return springBootBaseAppContext;
	}

	public static <T> T getBean(String name, Class<T> aClass) {
		return springBootBaseAppContext.getBean(name, aClass);
	}


}
