package com.kardibus.actionevent.annotation

import com.fasterxml.jackson.databind.ObjectMapper
import com.kardibus.actionevent.ActionService
import com.kardibus.model.action.Action
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.stereotype.Component

@Aspect
@Component
class AnnotationProcessor(val mapper: ObjectMapper, val action: ActionService) {

    @AfterReturning(
        value = "@annotation(com.kardibus.actionevent.annotation.ActionEventAnnotation)",
        returning = "result"
    )
    fun after(joinPoint: JoinPoint, result: Any?) {
        val method = (joinPoint.signature as MethodSignature).method
        val annotation = method.getAnnotation(ActionEventAnnotation::class.java)

        if (annotation != null) {
            println("Значение из аннотации: ${annotation.name}")
        }

        result?.let {
            action.execute(ByteArray(0), mapper.writeValueAsString(it).toByteArray(), Action())
            println(mapper.writeValueAsString(it))
            println("method ${joinPoint.signature} $result")
        }
    }

    @Before(value = "@annotation(com.kardibus.actionevent.annotation.ActionEventAnnotation)")
    fun before(joinPoint: JoinPoint) {
        val method = (joinPoint.signature as MethodSignature).method
        val annotation = method.getAnnotation(ActionEventAnnotation::class.java)

        if (annotation != null) {
            println("Значение из аннотации: ${annotation.name}")
        }

        action.execute(mapper.writeValueAsString(joinPoint.args).toByteArray(), ByteArray(0), Action())
        println(mapper.writeValueAsString(joinPoint.args))
        println("method ${joinPoint.signature} ${joinPoint.args.map { it.toString() }}")
    }
}
