package com.kardibus.actionevent.annotation

import com.kardibus.model.action.ActionName

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class ActionEvent(val name: ActionName)
