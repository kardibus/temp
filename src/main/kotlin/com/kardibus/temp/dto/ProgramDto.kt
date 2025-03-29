package com.kardibus.temp.dto

import com.kardibus.temp.model.programbeer.Program
import java.util.UUID

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
