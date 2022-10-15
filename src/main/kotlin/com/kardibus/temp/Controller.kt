package com.kardibus.temp

import com.kardibus.temp.model.BeerModel
import com.kardibus.temp.model.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller(private val service: Service) {

    @GetMapping("/")
    fun get(): Model {
        return service.getOneFirstModel()
    }

    @PostMapping
    fun push(@RequestBody beerModel: BeerModel) {
        println(beerModel)
    }
}