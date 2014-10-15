package hr.calyx.vjestina2014.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopLogging {

    static Logger logger = Logger.getLogger(AopLogging.class);

    @Pointcut("within(hr.calyx.vjestina2014.controllers.*)")
    public void anyController() {};

    @Around("anyController()")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object output = pjp.proceed();
        long elapsedTime = System.currentTimeMillis() - start;
        logger.info("ControllerMethodExecutionTime [method=" + pjp.getSignature().getName() + ", time=" + elapsedTime + "]");
        return output;
    }
}
