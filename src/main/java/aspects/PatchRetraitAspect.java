package aspects;

import metier.Compte;
import metier.MetierBanqueImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PatchRetraitAspect {

    @Pointcut("execution(* metier.MetierBanqueImpl.retirer(..))")
    public void pc1(){}

    @Around("pc1() && args(code, montant)")
    public Object aroundRetier(Long code, double montant, ProceedingJoinPoint pjp) throws Throwable {
        MetierBanqueImpl metier = (MetierBanqueImpl) pjp.getTarget();
        Compte compte = metier.consulter(code);
        if (compte.getSolde()<montant) throw new RuntimeException("Insufficient solde");
        return pjp.proceed();
    }

}
