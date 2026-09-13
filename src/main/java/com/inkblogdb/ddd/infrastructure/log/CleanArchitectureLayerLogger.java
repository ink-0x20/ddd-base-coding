package com.inkblogdb.ddd.infrastructure.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class CleanArchitectureLayerLogger {

  @Before("execution(* com.inkblogdb.ddd.presentation.api..*.*(..)))")
  public void apiStartLog(JoinPoint joinPoint){
    log.info("api:START:{}:{}", joinPoint.getSignature(), joinPoint.getArgs());
  }

  @AfterReturning(value = "execution(* com.inkblogdb.ddd.presentation.api..*.*(..)))", returning = "returnValue")
  public void apiEndLog(JoinPoint joinPoint, Object returnValue){
    log.info("api:END:{}.{}:{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), returnValue);
  }

  @AfterThrowing(value = "execution(* com.inkblogdb.ddd.presentation.api..*.*(..)))", throwing = "error")
  public void apiExceptionLog(JoinPoint joinPoint, Throwable error){
    log.warn("api:ERROR:{}:{}", joinPoint.getSignature(), error.getMessage());
  }

  @Before("execution(* com.inkblogdb.ddd.application.usecase..*UseCase.*(..)))")
  public void useCaseStartLog(JoinPoint joinPoint){
    log.info("UseCase:START:{}:{}", joinPoint.getSignature(), joinPoint.getArgs());
  }

  @AfterReturning(value = "execution(* com.inkblogdb.ddd.application.usecase..*UseCase.*(..)))", returning = "returnValue")
  public void useCaseEndLog(JoinPoint joinPoint, Object returnValue){
    log.info("UseCase:END:{}.{}:{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), returnValue);
  }

  @AfterThrowing(value = "execution(* com.inkblogdb.ddd.application.usecase..*UseCase.*(..)))", throwing = "error")
  public void useCaseExceptionLog(JoinPoint joinPoint, Throwable error){
    log.warn("UseCase:ERROR:{}:{}", joinPoint.getSignature(), error.getMessage());
  }

  @Before("execution(* com.inkblogdb.ddd.domain.model..*.*(..)))")
  public void domainStartLog(JoinPoint joinPoint){
    log.info("Domain:START:{}:{}", joinPoint.getSignature(), joinPoint.getArgs());
  }

  @AfterReturning(value = "execution(* com.inkblogdb.ddd.domain.model..*.*(..)))", returning = "returnValue")
  public void domainEndLog(JoinPoint joinPoint, Object returnValue){
    log.info("Domain:END:{}.{}:{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), returnValue);
  }

  @AfterThrowing(value = "execution(* com.inkblogdb.ddd.domain.model..*.*(..)))", throwing = "error")
  public void domainExceptionLog(JoinPoint joinPoint, Throwable error){
    log.warn("Domain:ERROR:{}:{}", joinPoint.getSignature(), error.getMessage());
  }

  @Before("execution(* com.inkblogdb.ddd.infrastructure.database..*.*(..)))")
  public void infrastructureStartLog(JoinPoint joinPoint){
    log.info("Infrastructure:START:{}:{}", joinPoint.getSignature(), joinPoint.getArgs());
  }

  @AfterReturning(value = "execution(* com.inkblogdb.ddd.infrastructure.database..*.*(..)))", returning = "returnValue")
  public void infrastructureEndLog(JoinPoint joinPoint, Object returnValue){
    log.info("Infrastructure:END:{}.{}:{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), returnValue);
  }

  @AfterThrowing(value = "execution(* com.inkblogdb.ddd.infrastructure.database..*.*(..)))", throwing = "error")
  public void infrastructureExceptionLog(JoinPoint joinPoint, Throwable error){
    log.error("Infrastructure:ERROR:{}:{}", joinPoint.getSignature(), error.getMessage());
  }

}
