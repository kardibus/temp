package com.kardibus.temp.controller

import com.kardibus.temp.model.Beer
import com.kardibus.temp.repository.BeerRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BeerController(private val beerRepository: BeerRepository) {

    @PostMapping
    fun createTempBeerWithDate(@RequestBody beer: Beer) {
        beerRepository.save(beer)
    }
}
