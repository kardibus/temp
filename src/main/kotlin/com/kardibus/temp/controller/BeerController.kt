package com.kardibus.temp.controller

import com.kardibus.temp.model.Beer
import com.kardibus.temp.service.BeerService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BeerController(private val beerService: BeerService) {

    @PostMapping
    fun createTempBeerWithDate(@RequestBody beer: Beer) {
        beerService.saveBeerModel(beer = beer)
    }
}
