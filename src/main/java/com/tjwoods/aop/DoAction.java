package com.tjwoods.aop;

import com.tjwoods.annotation.Action;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * 这个是切面类，也就是 处理 / 利用 自定义注解的类
 *
 * 执行顺序：around > before > around > after > afterReturning
 */
@Aspect
@Component
public class DoAction {

    /**
     * 切入点
     */
    @Pointcut("@annotation(com.tjwoods.annotation.Action)")
    public void execute() {}

    /**
     * 前置通知
     *
     * @param joinPoint
     */
    @Before(value = "execute()")
    public void before(JoinPoint joinPoint) {
        System.out.println("执行方法前");
    }

    /**
     * 环绕通知
     *
     * @param proceedingJoinPoint
     * @return
     */
    @Around(value = "execute()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("环绕通知开始");
        try {
            System.out.println("执行方法：" + proceedingJoinPoint.getSignature().getName());

            final MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
            final Action action = methodSignature.getMethod().getAnnotation(Action.class);
            System.out.println("注解的参数 action.description: " + action.description());

            final Object object = proceedingJoinPoint.proceed();
            System.out.println("环绕通知结束，方法返回：" + object);
            return object;
        } catch (Throwable e) {
            System.out.println("执行方法出现异常：" + e.getClass().getName());
            throw new IllegalStateException(e);
        }
    }

    /**
     * 后置通知
     * @param joinPoint
     */
    @After(value = "execute()")
    public void after(JoinPoint joinPoint) {
        System.out.println("执行方法后");
    }

    /**
     * 后置通知，带返回值
     * @param object
     */
    @AfterReturning(pointcut = "execute()", returning = "object")
    public void afterReturning(Object object) {
        System.out.println("执行方法之后，获取返回值：" + object);
    }

    /**
     * 后置通知，执行的方法出现异常时会调用这个
     * @param e
     */
    @AfterThrowing(pointcut = "execute()", throwing = "e")
    public void afterThrowing(Exception e) {
        System.out.println("执行方法异常：" + e.getClass().getName());
    }
}
