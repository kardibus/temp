package com.kardibus.temp.controller

import com.kardibus.temp.model.Beer
import com.kardibus.temp.model.Model
import com.kardibus.temp.service.BeerModelService
import com.kardibus.temp.service.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProgramController(private val service: Service, private val beerModelService: BeerModelService) {

    @GetMapping
    fun get(): Model {
        return service.getOneFirstModel()
    }

    @PostMapping
    fun push(@RequestBody beer: Beer) {
        beerModelService.saveBeerModel(beer = beer)
    }
}
