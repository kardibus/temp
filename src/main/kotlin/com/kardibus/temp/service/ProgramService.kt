package com.kardibus.temp.service

import com.kardibus.temp.model.programbeer.Program
import com.kardibus.temp.repository.ProgramRepository
import com.kardibus.temp.repository.StepRepository
import java.time.Clock
import java.time.Duration
import java.time.LocalDateTime
import java.util.UUID
import org.springframework.stereotype.Service

/**
 * Класс предназначен для расчета завершения программы
 */
@Service
class ProgramService(
    private val programRepository: ProgramRepository,
    private val clock: Clock,
) {

    fun calculateTimeWorkProgram(id: UUID): Program {
        val program = programRepository.findProgramById(id = id)
        var date = LocalDateTime.now(clock)

        if (program.work && !program.pause) {
            program.steps.filter { !it.done && it.work }.map { step ->

                if (step.dateStart == null) {
                    step.dateStart = date
                    step.dateEnd = date.plusMinutes(step.time.toLong())
                }

                if (step.dateEnd!!.isBefore(date)) {
                    step.done = true
                    step.work = false
                }

            }
        } else if (program.work && program.pause) {
            program.steps.filter { step -> !step.done && step.work }.map { step ->

                if (step.dateStart == null) {
                    step.dateStart = date
                }

                if (step.dateEnd == null) {
                    step.dateEnd = date.plusMinutes(step.time.toLong())
                }

                val time = Duration.between(step.dateStart, step.dateEnd)
                step.dateStart = date
                step.dateEnd = date.plus(time)
            }
        }
        programRepository.save(program)
        return program
    }
}