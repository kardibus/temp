package com.kardibus.temp.service

import com.kardibus.temp.dto.ProgramDto
import com.kardibus.temp.dto.StepDto
import com.kardibus.temp.model.programbeer.Program
import com.kardibus.temp.model.programbeer.Step
import com.kardibus.temp.repository.ProgramRepository
import org.springframework.stereotype.Service
import java.time.Clock
import java.time.Duration
import java.time.LocalDateTime
import java.util.UUID

/**
 * Класс предназначен для расчета завершения программы
 */
@Service
class ProgramService(private val clock: Clock, private val programRepository: ProgramRepository) {
    fun calculateTimeWorkProgram(id: UUID): Program {
        val program = programRepository.findProgramByUserId(id = id)
        val date = LocalDateTime.now(clock)

        if (program.work && !program.pause) {
            program.steps.filter { step -> !step.done && step.work }.sortedBy { step -> step.step }.map { step ->

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
            program.steps.filter { step -> !step.done && step.work }.sortedBy { step -> step.step }.map { step ->

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

    fun getPrograms(): List<ProgramDto> {
        val program = programRepository.findAll()
        if (program.isEmpty()) emptyList<ProgramDto>()
        return program.map {
            ProgramDto(
                id = it.id,
                name = it.name,
                work = it.work,
                pause = it.pause,
                steps = it.steps.map { StepDto(it) }.toMutableList(),
            )
        }
    }

    fun saveProgram(programDto: ProgramDto) {
        programRepository.save(
            Program().apply {
                name = programDto.name
                work = programDto.work
                pause = programDto.pause
                steps =
                    programDto.steps.map {
                        Step().apply {
                            step = it.step
                            time = it.time
                            dateStart = it.dateStart
                            dateEnd = it.dateEnd
                            done = it.done
                            work = it.work
                            temp = it.temp
                        }
                    }.toMutableList()
            },
        )
    }

    fun deleteProgram(id: UUID) {
        programRepository.deleteById(id)
    }
}
