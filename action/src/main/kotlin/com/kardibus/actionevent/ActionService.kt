package com.kardibus.actionevent

import com.kardibus.model.action.Action
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ActionService(
    private val em: EntityManager
) {

    @Transactional
    fun execute(input: ByteArray, output: ByteArray, action: Action) {
        action.input = input
        action.output = output
        save(action)
    }

    fun save(action: Action) {
        if (action.id == null) {
            em.persist(action)   // новый объект
        } else {
            em.merge(action)     // обновляем существующий
        }
    }


    fun findById(id: Long): Action? {
        return em.find(Action::class.java, id)
    }
}
