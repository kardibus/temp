package com.kardibus.temp.service

import com.kardibus.temp.model.Beer
import com.kardibus.temp.repository.BeerModelRepository

@org.springframework.stereotype.Service
class BeerModelService(private var beerModelRepository: BeerModelRepository) {
    fun saveBeerModel(beer: Beer) {
        beerModelRepository.save(beer)
    }
}