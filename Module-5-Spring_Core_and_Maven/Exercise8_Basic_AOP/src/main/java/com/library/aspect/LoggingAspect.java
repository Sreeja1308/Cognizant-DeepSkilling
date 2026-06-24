package com.library.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggingAspect {
    
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    
    @Pointcut("execution(* com.library.service.*.*(..))")
    public void serviceMethods() {}
    
    @Before("serviceMethods()")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        logger.info("BEFORE - Starting method: {}", methodName);
        
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            logger.info("Arguments: {}", java.util.Arrays.toString(args));
        }
    }
    
    @After("serviceMethods()")
    public void logAfter(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        logger.info("AFTER - Completed method: {}", methodName);
    }
    
    @AfterReturning(pointcut = "serviceMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().toShortString();
        logger.info("AFTER RETURNING - Method: {} returned: {}", methodName, result);
    }
    
    @AfterThrowing(pointcut = "serviceMethods()", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        String methodName = joinPoint.getSignature().toShortString();
        logger.error("AFTER THROWING - Method: {} threw exception: {}", methodName, error.getMessage());
    }
    
    @Around("serviceMethods()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        long startTime = System.currentTimeMillis();
        
        logger.info("AROUND - Entering method: {}", methodName);
        
        try {
            Object result = joinPoint.proceed();
            long endTime = System.currentTimeMillis();
            logger.info("AROUND - Method: {} executed in {} ms", methodName, (endTime - startTime));
            return result;
        } catch (Throwable t) {
            logger.error("AROUND - Exception in method: {}", methodName, t);
            throw t;
        } finally {
            logger.info("AROUND - Exiting method: {}", methodName);
        }
    }
}