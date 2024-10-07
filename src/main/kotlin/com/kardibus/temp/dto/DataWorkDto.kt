package com.kardibus.temp.dto

import java.util.UUID

data class DataWorkDto(
    var id: UUID,
    var prog: Long,
    var curr: Long,
    var temp: Double,
    var work: Boolean,
)