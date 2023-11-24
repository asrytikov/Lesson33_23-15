package org.example;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;


import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
public class LoggingAspect {

    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Around("execution(* org.example.CommentService.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();

        //logger.info("Method will execute");
        logger.info("Method " + methodName + " with parameters " + Arrays.asList(arguments)
                + " will execute");

        Comment comment = new Comment();
        comment.setText("Other text");
        Object[] newArgs = {comment};

        Object returnedByMethod = joinPoint.proceed(newArgs);
        logger.info("Method executed " + returnedByMethod);
        return "FAILED";
    }
}
