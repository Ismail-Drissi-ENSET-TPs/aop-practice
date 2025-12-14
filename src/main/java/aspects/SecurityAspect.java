package aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Scanner;

@Aspect
public class SecurityAspect {
    @Pointcut("execution(* com.ismaildrs.Main.start(..))")
    public void pc1(){}

    @Around("pc1()")
    public void aroundStart(ProceedingJoinPoint pjp) throws Throwable {
        Scanner sc = new Scanner(System.in);
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();

        if(username.equals("admin") && password.equals("admin")){
            pjp.proceed();
        }
        else{
            System.out.println("Access Denied");
        }
    }

}
