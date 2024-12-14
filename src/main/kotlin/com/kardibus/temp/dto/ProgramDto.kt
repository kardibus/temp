package com.kardibus.temp.dto

import com.kardibus.temp.model.programbeer.Program
import jakarta.validation.constraints.NotNull
import java.util.*

data class ProgramDto(
    var id: UUID,
    @NotNull(message = "Отсутствует название программы")
    var name: String,
    var work: Boolean,
    var pause: Boolean,
    var steps: MutableList<StepDto>,
) {
    constructor(program: Program) : this(
        id = program.id,
        name = program.name,
        work = program.work,
        pause = program.pause,
        steps = program.steps.map { StepDto(it) }.toMutableList(),
    )
}
