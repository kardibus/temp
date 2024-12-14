package com.kardibus.temp.dto

import java.util.UUID

data class DataWorkDto(
    var curr: Int = 0,
    var id: UUID,
    var prog: Int = 0,
    var temp: Double,
    var work: Boolean,
)
