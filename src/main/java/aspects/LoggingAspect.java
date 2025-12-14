package aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

@Aspect
public class LoggingAspect {
    long t1, t2;
    Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    public LoggingAspect() throws IOException {
        logger.addHandler(new FileHandler("log.xml"));
        logger.setUseParentHandlers(false);
    }

    @Pointcut("execution(* metier.*.*(..))")
    public void pointCut() {}

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        logger.info("before executing method: " + joinPoint.getSignature().getName());
        t1 = System.currentTimeMillis();
    }

    @After("pointCut()")
    public void after(JoinPoint joinPoint) {
        logger.info("after executing method: " + joinPoint.getSignature().getName());
        t2 = System.currentTimeMillis();
        logger.info("duration: " + (t2 - t1) + "ms");
    }


}
