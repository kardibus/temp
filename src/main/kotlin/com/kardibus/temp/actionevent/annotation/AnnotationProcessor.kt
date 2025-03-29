package com.kardibus.temp.actionevent.annotation

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.stereotype.Component

@Aspect
@Component
class AnnotationProcessor {
    @Around(value = "@annotation(com.kardibus.temp.actionevent.annotation.ActionEvent)")
    fun after(proceedingJoinPoint: ProceedingJoinPoint): Any {
        val signature = proceedingJoinPoint.getSignature() as MethodSignature
        val method: java.lang.reflect.Method? = signature.method
        println(proceedingJoinPoint.args.map { it })
        println(proceedingJoinPoint.proceed())
        return proceedingJoinPoint.proceed()
    }
}
