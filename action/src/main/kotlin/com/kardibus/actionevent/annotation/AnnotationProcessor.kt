package com.kardibus.actionevent.annotation

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

@Aspect
@Component
class AnnotationProcessor {
    @Around(value = "@annotation(com.kardibus.actionevent.annotation.ActionEvent)")
    fun after(proceedingJoinPoint: ProceedingJoinPoint): Any {
        println(proceedingJoinPoint.args.map { it })
        println(proceedingJoinPoint.proceed())
        return proceedingJoinPoint.proceed()
    }

}
