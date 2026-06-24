package com.library.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggingAspect {
    
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    
    @Pointcut("execution(* com.library.service.*.*(..))")
    public void serviceMethods() {}
    
    @Around("serviceMethods()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        
        String methodName = joinPoint.getSignature().toShortString();
        logger.info("Executing method: {}", methodName);
        
        try {
            Object result = joinPoint.proceed();
            
            long endTime = System.currentTimeMillis();
            logger.info("Method {} executed in {} ms", methodName, (endTime - startTime));
            
            return result;
        } catch (Throwable t) {
            logger.error("Exception in method {}: {}", methodName, t.getMessage());
            throw t;
        }
    }
}