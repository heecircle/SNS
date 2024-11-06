package com.heewon.sns.common.util;

import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@Aspect
public class TimeTraceAspect {

	@Pointcut("@annotation(com.heewon.sns.common.util.TimeTrace)")
	private void timeTracePointcut() {

	}

	@Around("timeTracePointcut()")
	public Object traceTime(ProceedingJoinPoint joinPoint) throws Throwable {
		StopWatch stopWatch = new StopWatch();

		try {
			stopWatch.start();
			return joinPoint.proceed();
		} finally {
			stopWatch.stop();
			log.info("{} - TotalTime is = {}s", joinPoint.getSignature().toShortString(), stopWatch.getTime());
		}
	}
}
