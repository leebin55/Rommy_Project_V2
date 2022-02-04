package com.roomy.aspect;

import com.roomy.trace.LogTrace;
import com.roomy.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
@RequiredArgsConstructor
public class LogTraceAspect {

    private final LogTrace logTrace;

    @Around("execution(* com.roomy.controller..*(..))|| execution(* com.roomy.service..*(..))" )
    public Object execute(ProceedingJoinPoint joinPoint)throws Throwable{
        TraceStatus status = null;
        try{
            String message = joinPoint.getSignature().toShortString();
            status = logTrace.begin(message);

            Object result = joinPoint.proceed(); // target

            logTrace.end(status);
            return result;
        }catch (Exception e){
            logTrace.exception(status,e);
            throw e;
        }
    }
}
