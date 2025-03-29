package com.kardibus.temp.dto

import com.kardibus.temp.model.programbeer.Step
import java.time.LocalDateTime
import java.util.UUID

data class StepDto(
    var dateEnd: LocalDateTime?,
    var dateStart: LocalDateTime?,
    var done: Boolean,
    var id: UUID?,
    var step: Int,
    var temp: Double,
    var time: Int,
    var work: Boolean,
) {
    constructor(step: Step) : this(
        id = step.id,
        step = step.step,
        time = step.time,
        dateStart = step.dateStart,
        dateEnd = step.dateEnd,
        done = step.done,
        work = step.work,
        temp = step.temp,
    )
}
