package com.seachangesimulations.platform.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;


/**
 * Saves the last update time. If the creation date is null, it saves the same time
 * to that field as well.
 * 
 *
 */
@Aspect
public class TimeStamper {
	
	@Pointcut ("execution(void *.save() )")
	public void save() {
		
	}
	
	@Before("save()")
	public void setLastUpdatedTime(JoinPoint joinpoint){
		
		System.out.println("     Aspect: TimeStamper.setLastUpdatedTime !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		
		if (joinpoint.getTarget() instanceof TimeStamped){
			System.out.println("    setting time stamp !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			TimeStamped ts = (TimeStamped) joinpoint.getTarget();
			ts.setLastUpdatedDate(new java.util.Date());
			if (ts.getCreatedDate() == null){
				ts.setCreatedDate(ts.getLastUpdatedDate());
			}
		}
	}
}
