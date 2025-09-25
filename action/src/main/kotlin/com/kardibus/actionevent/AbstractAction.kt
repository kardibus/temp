package com.kardibus.actionevent

import jakarta.persistence.EntityManager

abstract class AbstractAction<T, K>(val input: T, val output: K, val em: EntityManager) {

    fun execute(
        input: T,
        output: K,
    ) {
        val found = find(input)
        if(found != null) {

        } else {
            save(input)
        }
    }

    fun executeAndReturn(
        input: T,
        output: K,
    ): K {
        return output
    }

    fun save(input: T) {
        em.persist(input)
    }

    fun update(input: T) {

    }

    fun find(input: T): T? {
        return input
    }
}

