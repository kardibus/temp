package com.kardibus.temp.service

import com.kardibus.temp.dto.DataWorkDto
import java.util.UUID
import org.springframework.stereotype.Service

@Service
class DataWorkService(
    private val programService: ProgramService,
) {

    fun getDataWorkForUser(id: UUID): DataWorkDto? {
        val program = programService.calculateTimeWorkProgram(id = id)
        val step = program.steps
            .filter { step -> !step.done }
            .sortedBy { step -> step.step }
        if (step.isNullOrEmpty()) {
            return DataWorkDto(
                id = program.id,
                prog = program.steps.size,
                curr = 0,
                temp = 0.0,
                work = false
            )
        }
        return DataWorkDto(
            id = program.id,
            prog = program.steps.size,
            curr = step.first().step,
            temp = step.first().temp,
            work = step.first().work
        )
    }
}