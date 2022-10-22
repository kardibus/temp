package com.kardibus.temp.dao

import com.kardibus.temp.repository.StepRepository
import org.springframework.stereotype.Component

@Component
class StepDao(private var stepRepository: StepRepository) {

    fun getCountStep(id:Long):Long{
        return stepRepository.findByCountStep(id)
    }
}