package com.kardibus.temp.dto

import com.kardibus.temp.model.programbeer.Step
import java.time.LocalDateTime
import java.util.*

data class StepDto(
    var id: UUID,
    var step: Int,
    var time: Int,
    var dateStart: LocalDateTime?,
    var dateEnd: LocalDateTime?,
    var done: Boolean,
    var work: Boolean,
    var temp: Double,
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