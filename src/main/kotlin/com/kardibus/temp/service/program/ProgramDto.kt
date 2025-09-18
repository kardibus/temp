package com.kardibus.temp.service.program

import com.kardibus.temp.service.step.StepDto
import java.util.UUID
import model.programbeer.Program

data class ProgramDto(
    var id: UUID?,
    var name: String,
    var pause: Boolean,
    var steps: MutableList<StepDto>,
    var work: Boolean,
) {
    constructor(program: Program) : this(
        id = program.id,
        name = program.name,
        work = program.work,
        pause = program.pause,
        steps = program.steps.map { StepDto(it) }.toMutableList(),
    )
}
