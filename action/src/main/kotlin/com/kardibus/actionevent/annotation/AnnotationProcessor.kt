package com.kardibus.actionevent.annotation

import com.fasterxml.jackson.databind.ObjectMapper
import com.kardibus.actionevent.Action
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.stereotype.Component

@Aspect
@Component
class AnnotationProcessor(val mapper: ObjectMapper, val action: Action) {

    @AfterReturning(
        value = "@annotation(com.kardibus.actionevent.annotation.ActionEvent)",
        returning = "result"
    )
    fun after(joinPoint: JoinPoint, result: Any?) {
        val method = (joinPoint.signature as MethodSignature).method
        val annotation = method.getAnnotation(ActionEvent::class.java)

        if (annotation != null) {
            println("Значение из аннотации: ${annotation.name}")
        }

        result?.let {
            println(mapper.writeValueAsString(it))
            println("method ${joinPoint.signature} $result")
        }
    }

    @Before(value = "@annotation(com.kardibus.actionevent.annotation.ActionEvent)")
    fun before(joinPoint: JoinPoint) {
        val method = (joinPoint.signature as MethodSignature).method
        val annotation = method.getAnnotation(ActionEvent::class.java)

        if (annotation != null) {
            println("Значение из аннотации: ${annotation.name}")
        }

        println(mapper.writeValueAsString(joinPoint.args))
        println("method ${joinPoint.signature} ${joinPoint.args.map { it.toString() }}")
    }
}
