package com.kardibus.temp.actionevent

abstract class Action<T, K>(val input: T, val output: K) {
    fun execute(
        input: T,
        output: K,
    ) {
    }

    fun executeAndReturn(
        input: T,
        output: K,
    ): Any {
        return Any()
    }

    fun save() {}

    fun update() {}
}
