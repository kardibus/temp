package com.kardibus.temp.dto

import java.time.LocalDateTime

class BeerDto {
    var id: Long? = null
    var temp: Double = 0.0
    var date: LocalDateTime = LocalDateTime.now()
}
