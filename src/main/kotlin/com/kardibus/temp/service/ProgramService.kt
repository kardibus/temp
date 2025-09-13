package com.kardibus.temp.service

import com.kardibus.temp.dto.ProgramDto
import com.kardibus.temp.dto.StepDto
import com.kardibus.temp.model.programbeer.Program
import com.kardibus.temp.model.programbeer.Step
import com.kardibus.temp.repository.ProgramRepository
import com.kardibus.temp.repository.StepRepository
import com.kardibus.temp.utils.common.findByIdOrThrow
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
    private val clock: Clock,
    private val programRepository: ProgramRepository,
    private val stepRepository: StepRepository
) {
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
        val programSaved = programRepository.save(
            Program().apply {
                name = programDto.name
                work = programDto.work
                pause = programDto.pause
            }
        )
        val mutableList = programDto.steps.map {
            Step().apply {
                step = it.step
                time = it.time
                dateStart = it.dateStart
                dateEnd = it.dateEnd
                done = it.done
                work = it.work
                temp = it.temp
                program = programSaved
            }
        }.toMutableList()

        stepRepository.saveAll(mutableList)
    }

    fun updateProgram(programDto: ProgramDto) {
        val prog = programRepository.findByIdOrThrow(programDto.id!!)

            val programEntity = prog.apply {
                name = programDto.name
                work = programDto.work
                pause = programDto.pause
            }

            val steps = programDto.steps.map {
                Step().apply {
                    id = it.id
                    step = it.step
                    time = it.time
                    dateStart = it.dateStart
                    dateEnd = it.dateEnd
                    done = it.done
                    work = it.work
                    temp = it.temp
                    program = programEntity
                }
            }.toMutableList()

            programRepository.save(programEntity)
            stepRepository.saveAll(steps)
    }

    fun deleteProgram(id: UUID) {
        programRepository.deleteById(id)
    }

    fun changeWork(id: UUID, work: Boolean) {
        programRepository.changeWorkById(id = id, work = work)
    }

    fun changePause(id: UUID, pause: Boolean) {
        programRepository.changePauseById(id = id, pause = pause)
    }
}
