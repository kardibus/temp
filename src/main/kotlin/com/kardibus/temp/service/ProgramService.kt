package com.kardibus.temp.service

import com.kardibus.temp.model.programbeer.Program
import com.kardibus.temp.repository.ProgramRepository
import java.time.Duration
import java.time.LocalDateTime
import org.springframework.stereotype.Service

/**
 * Класс предназначен для расчета завершения программы
 */
@Service
class ProgramService(
    private var programRepository: ProgramRepository,
) {

    fun calculateTimeWorkProgram(program: Program): Program {
        var date = LocalDateTime.now()

        if (program.work && !program.pause) {
            program.steps?.filter { !it.done && it.work }?.map { step ->

                if (step.dateStart == null && !step.done && step.work) {
                    step.dateStart = date
                    step.dateEnd = date.plusMinutes(step.time.toLong())

                    programRepository.save(program)
                }
                if (step.dateEnd!!.isBefore(date)) {
                    step.done = true
                    step.work = false
                    programRepository.save(program)
                }
            }?.toList()
        } else if (program.work && program.pause) {
            program.steps?.filter { step -> !step.done && step.work }?.map { step ->

                val time = Duration.between(date, step.dateEnd)
                step.dateEnd = date.plus(time)
            }
            programRepository.save(program)
        }

        return program
    }
}
