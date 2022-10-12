package com.kardibus.temp

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.math.RoundingMode
import kotlin.random.Random

@RestController
class Controller {

    @GetMapping("/")
    fun get(): Model {
        return Model().apply {
            temp = Random.nextDouble(0.0,100.0).toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
            work = Random.nextBoolean()
        }
    }
}