package com.kardibus.temp.service

import com.kardibus.temp.repository.StepRepository
import java.util.UUID
import org.springframework.data.jpa.repository.Modifying
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StepService(private val stepRepository: StepRepository) {

    @Modifying
    @Transactional
    fun deleteStepById(id: UUID) {
        stepRepository.deleteStepById(id)
    }
}
