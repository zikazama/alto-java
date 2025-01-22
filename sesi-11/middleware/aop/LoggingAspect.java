@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* com.example.demo.controller.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Request to method: " + joinPoint.getSignature().getName());
    }

    @After("execution(* com.example.demo.controller.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("Method execution completed: " + joinPoint.getSignature().getName());
    }
}
