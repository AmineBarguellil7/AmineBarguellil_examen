package com.example.aminebarguellil.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;






@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @AfterReturning("execution( void com.example.aminebarguellil.Controller.*.*(..))")
    public void logMethodEntry(JoinPoint joinPoint) {

        String name = joinPoint.getSignature().getName();

        log.info("In method :" + name );
    }
}
