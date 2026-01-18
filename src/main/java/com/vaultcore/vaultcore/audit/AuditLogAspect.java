package com.vaultcore.vaultcore.audit;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditLogAspect {

    @Around("execution(* com.vaultcore..*(..))")
    public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {

        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();

        System.out.println("📘 AUDIT LOG - Method Called: " + methodName);

        if (args != null) {
            for (Object arg : args) {
                System.out.println("   ➤ Parameter: " + arg);
            }
        }

        Object result = joinPoint.proceed();

        System.out.println("📗 AUDIT LOG - Method Completed: " + methodName);
        System.out.println("   ➤ Return Value: " + result);

        return result;
    }
}
