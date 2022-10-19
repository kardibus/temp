package com.kardibus.temp.dto

import com.kardibus.temp.model.programbeer.Program
import com.kardibus.temp.model.programbeer.Step
import kotlin.streams.toList

class MapperImp : Mapper<Step, Program, ProgramDto> {
    override fun to(entity: List<Step>, entityTwo: Program): ProgramDto {
        return ProgramDto().apply {
            id = entityTwo.id
            name = entityTwo.name
            work = entityTwo.work
            steps = entity.stream().map { a ->
                Step().apply {
                    id = a.id
                    step = a.step
                    time = a.time
                    prog_id = a.prog_id
                }
            }.toList()
        }
    }

    override fun from(domain: ProgramDto): List<Step> {
        return domain.steps?.toList() ?: listOf()
    }

    override fun fromTwo(domain: ProgramDto): Program {
        return Program().apply {
            id = domain.id
            name = domain.name
            work = domain.work
        }
    }
}
