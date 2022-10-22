package com.kardibus.temp.controller

import com.kardibus.temp.dao.BeerDao
import com.kardibus.temp.model.Beer
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BeerController(private val beerDao: BeerDao) {

    @PostMapping
    fun createTempBeerWithDate(@RequestBody beer: Beer) {
        beerDao.saveBeerModel(beer = beer)
    }
}
