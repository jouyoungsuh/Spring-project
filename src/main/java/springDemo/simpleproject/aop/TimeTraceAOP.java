package springDemo.simpleproject.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeTraceAOP {
    @Around("execution(* springDemo.simpleproject..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        System.out.println("Start: " + joinPoint.toString());

        try {
            return joinPoint.proceed();
        }
        finally {
            long finishTime = System.currentTimeMillis();
            long totalTimeMs = finishTime - startTime;
            System.out.println("End: " + joinPoint.toString()+ " " + totalTimeMs + "ms");
        }
    }
}
