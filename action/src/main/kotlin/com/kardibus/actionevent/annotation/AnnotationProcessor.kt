package com.kardibus.actionevent.annotation

import com.fasterxml.jackson.databind.ObjectMapper
import com.kardibus.actionevent.ActionService
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.stereotype.Component

@Aspect
@Component
class AnnotationProcessor(val mapper: ObjectMapper, val action: ActionService) {

    @Around(value = "@annotation(com.kardibus.actionevent.annotation.ActionEventAnnotation)")
    fun aroundAction(joinPoint: ProceedingJoinPoint): Any? {
        val method = (joinPoint.signature as MethodSignature).method
        val annotation = method.getAnnotation(ActionEventAnnotation::class.java)

        if (annotation != null) {
            println("Значение из аннотации: ${annotation.name}")
        }

        val result = joinPoint.proceed()

        action.execute(mapper.writeValueAsString(joinPoint.args).toByteArray(),mapper.writeValueAsString(result).toByteArray())

        return result
    }
}
