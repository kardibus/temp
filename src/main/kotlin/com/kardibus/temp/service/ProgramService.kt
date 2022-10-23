package com.kardibus.temp.service

import com.kardibus.temp.dao.ModelDao
import com.kardibus.temp.dao.ProgramDao
import com.kardibus.temp.dao.StepDao
import com.kardibus.temp.model.Model
import com.kardibus.temp.model.programbeer.Program
import com.kardibus.temp.model.programbeer.Step
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ProgramService(private var programDao: ProgramDao, private var modelDao: ModelDao, private var stepDao: StepDao) {

    @Scheduled(fixedRate = 40000)
    fun timeWorkProgram() {
        var program = getProgram()
        var date = LocalDateTime.now()



        if (program.work && !program.pause) {

            val steps = programDao.stepByProg_idNotDone(program.id!!.toLong())

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
            val steps = programDao.stepByProg_idNotDone(program.id!!.toLong())
            steps.stream().map { s->
                programDao.saveStep(s.apply {
                    toDate = s.toDate!!.plusSeconds(40)
                })
            }.toArray()
        }

        if (!program.work) {
            modelDao.updeteModel(getModel().apply {
                temp = 0.0
                prog = 0
                curr = 0
                work = false
            })
        }
    }

    fun model(program: Program, steps: Step) {
        val model = modelDao.getByIdModel().get()

        modelDao.updeteModel(model.apply {
            prog = stepDao.getCountStep(program.id!!)
            curr = steps.step!!
            temp = steps.temp.toDouble()
            work = program.work
        })

    }

    fun getProgram() = if (!programDao.programTrue().isEmpty) programDao.programTrue().get() else Program()
    fun getModel() = if (!modelDao.getModel().isEmpty) modelDao.getModel().get() else Model()
}
