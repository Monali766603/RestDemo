package com.yash.student.services;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogService {
	Logger LOGGER;

	@Pointcut(value = "within(com.yash.student.controller.StudentController)")
	public void StudentControllerPointcut() {
	}

	@Pointcut(value = "within(com.yash.services.StudentImpl)")
	public void StudentImplPointcut() {
	}

	@Before("StudentControllerPointcut() || StudentImplPointcut()")
	public void logBefore(JoinPoint joinPoint) {
		LOGGER = Logger.getLogger(joinPoint.getSignature().getDeclaringTypeName());
		LOGGER.log(Level.INFO, "Before : " + joinPoint.getSignature().getName());
	}

	@AfterReturning("StudentControllerPointcut() || StudentImplPointcut()")
	public void logReturning(JoinPoint joinPoint) {
		LOGGER = Logger.getLogger(joinPoint.getSignature().getDeclaringTypeName());
		LOGGER.log(Level.INFO, "Returning from : " + joinPoint.getSignature().getName());
	}

	@AfterThrowing("StudentControllerPointcut() || StudentImplPointcut()")
	public void logThrowing(JoinPoint joinPoint) {
		LOGGER = Logger.getLogger(joinPoint.getSignature().getDeclaringTypeName());
		LOGGER.log(Level.INFO, "Throwing Exception from : " + joinPoint.getSignature().getName());
	}

	@After("StudentControllerPointcut() || StudentImplPointcut()")
	public void logAfter(JoinPoint joinPoint) {
		LOGGER = Logger.getLogger(joinPoint.getSignature().getDeclaringTypeName());
		LOGGER.log(Level.INFO, "After : " + joinPoint.getSignature().getName());
	}
}



