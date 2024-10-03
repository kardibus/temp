package com.kardibus.temp.service

import com.kardibus.temp.dao.ProgramService
import com.kardibus.temp.model.brewery.DataWork
import com.kardibus.temp.model.programbeer.Program
import com.kardibus.temp.model.programbeer.Step
import com.kardibus.temp.repository.ModelRepository
import com.kardibus.temp.repository.StepRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ProgramService(
    private var programService: ProgramService,
    private var modelRepository: ModelRepository,
    private var stepRepository: StepRepository
) {

    fun timeWorkProgram() {
        var program = getProgram()
        var date = LocalDateTime.now()

        if (program.work && !program.pause) {
            programService.getStepByProg_idNotDone(program.id!!.toLong()).map { s ->
                if (s == null) {
                    programService.updateProgram(
                        program.apply {
                            work = false
                        }
                    )
                }

                if (s.fromDate == null && !s.done && s.toDate == null) {
                    programService.saveStep(
                        s.apply {
                            fromDate = date
                            toDate = date.plusMinutes(s.time!!.toLong())
                        }
                    )
                }
                if (s.toDate!!.isBefore(date)) {
                    programService.saveStep(
                        s.apply {
                            done = true
                        }
                    )
                }
                model(program, s)
            }.toArray()
        }

        if (program.work && program.pause) {
            programService.getStepByProg_idNotDone(program.id!!.toLong()).map { s ->
                programService.saveStep(
                    s.apply {
                        toDate = s.toDate!!.plusSeconds(40)
                    }
                )
            }.toArray()
        }

        if (!program.work) {
            model(
                Program().apply {
                    id = 0
                    work = false
                },
                Step().apply {
                    temp = 0
                    step = 0
                }
            )
        }
    }

    fun model(program: Program, steps: Step) {
        val model = modelRepository.findByProg().get()

        modelRepository.save(
            model.apply {
                prog = stepRepository.findByCountStep(program.id!!)
                curr = steps.step!!
                temp = steps.temp.toDouble()
                work = program.work
            }
        )
    }

    fun getProgram() = if (!programService.getProgramTrue().isEmpty) programService.getProgramTrue().get() else Program()
    fun getModel() = if (!modelRepository.findByProg().isEmpty) modelRepository.findByProg().get() else DataWork()
}
