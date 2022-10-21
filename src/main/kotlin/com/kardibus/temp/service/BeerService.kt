package com.kardibus.temp.service

import com.kardibus.temp.model.Beer
import com.kardibus.temp.repository.BeerRepository

@org.springframework.stereotype.Service
class BeerService(private var beerRepository: BeerRepository) {
    fun saveBeerModel(beer: Beer) {
        beerRepository.save(beer)
    }
}
