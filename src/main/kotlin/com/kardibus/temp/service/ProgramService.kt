package com.kardibus.temp.service

import com.kardibus.temp.dao.ProgramDao
import com.kardibus.temp.model.Model
import com.kardibus.temp.model.programbeer.Program
import com.kardibus.temp.model.programbeer.Step
import com.kardibus.temp.repository.ModelRepository
import com.kardibus.temp.repository.StepRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ProgramService(
    private var programDao: ProgramDao,
    private var modelRepository: ModelRepository,
    private var stepRepository: StepRepository
) {

    @Scheduled(fixedRate = 40000)
    fun timeWorkProgram() {
        var program = getProgram()
        var date = LocalDateTime.now()



        if (program.work && !program.pause) {

            val steps = programDao.getStepByProg_idNotDone(program.id!!.toLong())

            if (steps.isEmpty()) programDao.updateProgram(program.apply {
                work = false
            })

            steps.stream().map { s ->
                if (s.fromDate == null && !s.done && s.toDate == null) {

                    programDao.saveStep(s.apply {
                        fromDate = date
                        toDate = date.plusMinutes(s.time!!.toLong())
                    })
                }
                if (s.toDate!!.isBefore(date)) {
                    programDao.saveStep(s.apply {
                        done = true
                    })
                }
                model(program, s)
            }.toArray()
        }

        if (program.work && program.pause) {
            val steps = programDao.getStepByProg_idNotDone(program.id!!.toLong())
            steps.stream().map { s ->
                programDao.saveStep(s.apply {
                    toDate = s.toDate!!.plusSeconds(40)
                })
            }.toArray()
        }

        if (!program.work) {
            modelRepository.save(getModel().apply {
                temp = 40.0
                prog = 0
                curr = 0
                work = false
            })
        }
    }
    @CacheEvict(value= ["name"],allEntries=true)
    fun model(program: Program, steps: Step) {
        val model = modelRepository.findByProg().get()

        modelRepository.save(model.apply {
            prog = stepRepository.findByCountStep(program.id!!)
            curr = steps.step!!
            temp = steps.temp.toDouble()
            work = program.work
        })

    }

    fun getProgram() = if (!programDao.getProgramTrue().isEmpty) programDao.getProgramTrue().get() else Program()
    fun getModel() = if (!modelRepository.findByProg().isEmpty) modelRepository.findByProg().get() else Model()
}
