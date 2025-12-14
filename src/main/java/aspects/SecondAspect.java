package aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class SecondAspect {

    // Corrected pointcut to match the standard main method signature
    @Pointcut("execution(* com.ismaildrs.Main.main(..))")
    public void pointCut() { }

    @Before("pointCut()")
    public void before() {
        System.out.println("before executing");
    }

    @After("pointCut()")
    public void after() {
        System.out.println("after executing");
    }
}