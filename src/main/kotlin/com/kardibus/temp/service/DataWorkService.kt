package com.kardibus.temp.service

import com.kardibus.temp.dto.DataWorkDto
import java.util.UUID
import org.springframework.stereotype.Service

@Service
class DataWorkService(private val programService: ProgramService) {

    fun getDataWork(id: UUID): DataWorkDto? {
        val program = programService.calculateTimeWorkProgram(id = id)
        val step = program.steps.filter { step -> !step.done }.first()

        return DataWorkDto(
            id = program.id,
            prog = program.steps.size,
            curr = step.step,
            temp = step.temp,
            work = step.work
        )
    }
}