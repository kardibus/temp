package com.kardibus.temp.controller

import com.kardibus.temp.dto.BeerDto
import com.kardibus.temp.model.brewery.Beer
import com.kardibus.temp.repository.BeerRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/** Принимаем температуру с датчика микроконтроллера */
@RestController
class BeerController(private val beerRepository: BeerRepository) {

    @PostMapping
    fun createTempBeerWithDate(@RequestBody beer: BeerDto) {
        beerRepository.save(Beer().apply {
            temp = beer.temp
            date = beer.date
        })
    }
}