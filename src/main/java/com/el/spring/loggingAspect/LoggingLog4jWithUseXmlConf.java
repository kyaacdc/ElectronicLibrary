package com.el.spring.loggingAspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;

public class LoggingLog4jWithUseXmlConf {

    Logger log = Logger.getLogger(getClass());

    public void beforeAdvice(JoinPoint joinpoint) {
        log.info("***************************************** Start beforeAdvice **************************************");
        log.info("ASPECT INFO: Now we are going to initiate class.");

        Object clazz = joinpoint.getTarget().getClass().getName();
        String methodName = joinpoint.getSignature().getName();
        log.info("Entering to Class " + clazz + " With Method Name " + methodName);
        if (log.isDebugEnabled()) {
            Object[] obj = joinpoint.getArgs();
            for (Object o : obj)
                log.info("Parameter Name..." + o != null ? o.toString() : "");
        }
        log.info("***************************************** End beforeAdvice **************************************");
    }

    public void afterAdvice(JoinPoint joinpoint) {
        log.info("***************************************** Start afterAdvice **************************************");
        log.info("ASPECT INFO: Class has been initiated.");
        Object clazz = joinpoint.getTarget().getClass().getName();
        String methodName = joinpoint.getSignature().getName();
        log.info("After Entring to Method " + methodName+ " in Calss " + clazz);

        log.info(joinpoint.getSignature().getName() + " called...");
        log.info("***************************************** End afterAdvice **************************************");
    }

    public void afterReturningAdvice(JoinPoint joinpoint, Object someValue) {
        log.info("***************************************** Start afterReturningAdvice **************************************");
        log.info("ASPECT INFO: Value: " + someValue.toString());
        Object clazz = joinpoint.getTarget().getClass().getName();
        String methodName = joinpoint.getSignature().getName();
        log.info("After Returning From Method " + methodName + " in Class " + clazz);
        log.info("***************************************** End afterReturningAdvice **************************************");
    }

    public void inCaseOfExceptionThrowAdvice(JoinPoint joinpoint, ClassCastException e) {
        log.info("ASPECT INFO: We have an exception here: " + e.toString());
        Object clazz = joinpoint.getTarget().getClass().getName();
        String methodName = joinpoint.getSignature().getName();
        log.info("After Throwing From Method " + methodName + " in Calss " + clazz);
        log.error("After Throwing From Method " + methodName + " in Calss " + clazz, e);
        e.printStackTrace();
    }

}