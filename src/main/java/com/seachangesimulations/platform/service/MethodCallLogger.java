package com.seachangesimulations.platform.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MethodCallLogger {

	// "execution(public * *(..))"
	@Pointcut ("execution(* *(..))")
	public void methodCalled() {
		
	}
	
	@Before("methodCalled()")
	public void setLastUpdatedTime(JoinPoint joinpoint){
		
		System.out.println("MCL CALLED:" + joinpoint.getClass().getSimpleName() + ":" + joinpoint.getSignature());
	}
}
