package com.kardibus.temp.dao

import com.kardibus.temp.dto.ProgramDto
import com.kardibus.temp.dto.mapper.MapperImp
import com.kardibus.temp.model.programbeer.Program
import com.kardibus.temp.model.programbeer.Step
import com.kardibus.temp.repository.ProgramRepository
import com.kardibus.temp.repository.StepRepository
import org.springframework.stereotype.Component
import java.util.Optional
import kotlin.streams.toList

@Component
class ProgramDao(private val programRepository: ProgramRepository, private var stepRepository: StepRepository) {

    fun getAllProgram(): List<ProgramDto> {
        var list: MutableList<ProgramDto> = ArrayList()
        var step: MutableList<Step> = stepRepository.findAll()
        programRepository.findAll().stream().map { prg ->
            list.add(MapperImp().toProgramDto(step.stream().filter { a -> a.prog_id == prg.id }.toList(), prg))
        }.toArray()

        return list
    }

    fun getFindByIdProgram(id: Long): List<ProgramDto> {
        var list: MutableList<ProgramDto> = ArrayList()
        var step: MutableList<Step> = stepRepository.findAll()
        programRepository.findById(id).stream().map { prg ->
            list.add(MapperImp().toProgramDto(step.stream().filter { a -> a.prog_id == prg.id }.toList(), prg))
        }.toArray()

        return list
    }

    fun deleteProgram(id: Long) {
        var listStep = stepRepository.findByProg_id(id)
        stepRepository.deleteAll(listStep)

        programRepository.delete(programRepository.findById(id).get())
    }

    fun updateProgram(map: List<ProgramDto>) {
        map.stream().map { prog ->
            var progBase = programRepository.findById(prog.id!!)
            var stepBase = stepRepository.findByProg_id(prog.id!!)
            var steps = prog.steps

            if (!progBase.isEmpty) {
                programRepository.save(
                    progBase.get().apply {
                        name = prog.name
                        work = prog.work
                    }
                )

                if (stepBase.isNotEmpty()) {
                    steps!!.stream().map { s ->
                        var st = stepBase.stream().filter { st -> st.id == s.id }

                        stepRepository.save(
                            st.toList().get(0).apply {
                                step = s.step
                                time = s.time
                                prog_id = s.prog_id
                            }
                        )
                    }.toArray()
                }
            }
        }.toArray()
    }

    fun createProgram(map: List<ProgramDto>) {
        map.stream().map { prog ->
            var steps = prog.steps!!.toList()
            var prog_id = programRepository.getIdProg()

            programRepository.save(MapperImp().fromProgram(prog))

            steps.forEach { a ->
                a.prog_id = prog_id
            }

            stepRepository.saveAll(steps)
        }.toArray()
    }

    fun programTrue(): Optional<Program> {
        return programRepository.getProgEnable()
    }

    fun stepByProg_idNotDone(id: Long): List<Step> {
        return stepRepository.findByProg_idNotDone(id)
    }

    fun saveStep(step: Step){
        stepRepository.save(step)
    }
}
