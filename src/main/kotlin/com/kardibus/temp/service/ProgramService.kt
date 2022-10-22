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

    @Scheduled(fixedRate = 100000)
    fun timeWorkProgram() {
        var prog = getProgram()
        var date = LocalDateTime.now()


        if (prog.work && !prog.pause) {

            var steps = programDao.stepByProg_idNotDone(prog.id!!.toLong())

            if (steps.isEmpty()) programDao.updateProgram(prog.apply {
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
                model(prog, s)
            }.toArray()
        }
        if (!prog.work) {
            modelDao.updeteModel(modelDao.getModel().get().apply {
                work = false
            })
        }
    }

    fun model(program: Program, steps: Step) {
        var model = modelDao.getByIdModel().get()

        modelDao.updeteModel(model.apply {
            prog = getCountStep(program.id!!)
            curr = steps.step!!
            temp = steps.temp.toDouble()
            work = program.work
        })

    }

    fun getProgram() = if (!programDao.programTrue().isEmpty) programDao.programTrue().get() else Program()
    fun getModel() = if (!modelDao.getModel().isEmpty) modelDao.getModel().get() else Model()

    fun getCountStep(id: Long) = stepDao.getCountStep(id)
}
