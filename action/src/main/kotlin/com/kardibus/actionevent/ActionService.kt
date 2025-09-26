package com.kardibus.actionevent

import com.kardibus.model.action.Action
import jakarta.persistence.EntityManager
import java.time.LocalDateTime
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ActionService(
    private val em: EntityManager
) {

    @Transactional
    fun execute(input: ByteArray, output: ByteArray): Action {
        var entity = Action()
        entity.input = input
        entity.output = output
        entity.completionDate = LocalDateTime.now()

        return saveOrUpdate(entity)
    }

    fun saveOrUpdate(action: Action): Action {
        em.persist(action)
        return action
    }
}
