package com.kardibus.temp.dto

import com.kardibus.temp.model.programbeer.Step
import java.util.UUID

data class ProgramDto(
    var id: UUID,
    var name: String,
    var work: Boolean,
    var pause: Boolean,
    var steps: MutableList<Step>
)
