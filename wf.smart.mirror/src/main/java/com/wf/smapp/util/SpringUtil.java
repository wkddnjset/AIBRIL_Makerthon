/**
 * 
 */
package com.wf.smapp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author kht
 *
 */
@Component
public class SpringUtil {
	 public static ApplicationContext ctx;
	 
	 @Autowired
	 private void setApplicationContext(ApplicationContext applicationContext) {
		 ctx = applicationContext;       
	 }
}
