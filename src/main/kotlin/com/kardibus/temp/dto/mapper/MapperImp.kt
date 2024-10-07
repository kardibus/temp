package com.kardibus.temp.dto.mapper

import com.kardibus.temp.dto.ProgramDto
import com.kardibus.temp.model.programbeer.Program
import com.kardibus.temp.model.programbeer.Step

class MapperImp : Mapper<Step, Program, ProgramDto> {
    override fun toProgramDto(entity: List<Step>, entityTwo: Program): ProgramDto {
        return entityTwo.steps.let {
            ProgramDto(
                entityTwo.id,
                entityTwo.name,
                entityTwo.work,
                entityTwo.pause,
                it?.map { a ->
                    Step().apply {
                        id = a.id
                        step = a.step
                        time = a.time
                        dateStart = a.dateStart
                        dateEnd = a.dateEnd
                    }
                } as MutableList<Step>
            )
        }
    }

    override fun fromProgram(domain: ProgramDto): Program {
        return Program().apply {
            id = domain.id
            name = domain.name
            work = domain.work
            pause = domain.pause
            steps = domain.steps
        }
    }
}
