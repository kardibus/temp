package com.kardibus.temp.service.step

import org.springframework.data.jpa.repository.Modifying
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class StepService(private val stepRepository: StepRepository) {
    @Modifying
    @Transactional
    fun deleteStepById(id: UUID) {
        stepRepository.deleteStepById(id)
    }
}
