package com.kardibus.temp

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
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
            prog = Random.nextInt(0,10)
        }
    }

    @PostMapping
    fun push(@RequestBody beerModel: BeerModel){
        println(beerModel)
    }
}