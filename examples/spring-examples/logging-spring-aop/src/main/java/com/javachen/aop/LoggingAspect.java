package com.javachen.aop;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggingAspect {
	
	public Object logService(final ProceedingJoinPoint joinPoint) throws Throwable {
		Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
		long currentTicks = System.currentTimeMillis();
		try {
			Object result = joinPoint.proceed();
			logger.info(StringUtils.join(joinPoint.getSignature().getName(), "() executed in ", Long.toString(System.currentTimeMillis() - currentTicks), "ms"));
			return result;
		} catch (Throwable ex) {
			logger.info(StringUtils.join(joinPoint.getSignature().getName(), "() failed in ", Long.toString(System.currentTimeMillis() - currentTicks), "ms"));
			throw ex;
		}
	}
}
