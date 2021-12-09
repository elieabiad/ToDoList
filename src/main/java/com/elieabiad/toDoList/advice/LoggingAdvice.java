package com.elieabiad.toDoList.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class LoggingAdvice {

    Logger log = LoggerFactory.getLogger(LoggingAdvice.class);

    @Pointcut(value="execution(* com.elieabiad.toDoList.controller.*.*(..))")  //https://www.youtube.com/watch?v=RVvKPP5HyaA&t=11s 13:30
    public void myPointcut(){

    }
    @Around("myPointcut()")
    public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        String methodeName = pjp.getSignature().getName();
        String className = pjp.getTarget().getClass().toString();
        Object[] array = pjp.getArgs();
        log.info("Method invoked " + className + " : " + methodeName);
               // + "() arguments : " + mapper.writeValueAsString(array));
        Object object = pjp.proceed();
        log.info(className + " : " + methodeName
                + "() Response : " + object);

        return object;
    }
}
