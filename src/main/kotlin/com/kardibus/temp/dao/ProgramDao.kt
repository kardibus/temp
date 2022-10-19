package com.kardibus.temp.dao

import com.kardibus.temp.dto.MapperImp
import com.kardibus.temp.dto.ProgramDto
import com.kardibus.temp.model.programbeer.Step
import com.kardibus.temp.repository.ProgramRepository
import com.kardibus.temp.repository.StepRepository
import org.springframework.stereotype.Component
import kotlin.streams.toList

@Component
class ProgramDao(private val programRepository: ProgramRepository, private var stepRepository: StepRepository) {

    fun getAllProgram(): List<ProgramDto> {
        var list: MutableList<ProgramDto> = ArrayList()
        var step: MutableList<Step> = stepRepository.findAll()
        programRepository.findAll().stream().map { prg ->
            list.add(MapperImp().to(step.stream().filter { a -> a.prog_id == prg.id }.toList(), prg))
        }.toArray()

        return list
    }

    fun getFindByIdProgram(id: Long): List<ProgramDto> {
        var list: MutableList<ProgramDto> = ArrayList()
        var step: MutableList<Step> = stepRepository.findAll()
        programRepository.findById(id).stream().map { prg ->
            list.add(MapperImp().to(step.stream().filter { a -> a.prog_id == prg.id }.toList(), prg))
        }.toArray()

        return list
    }
}
