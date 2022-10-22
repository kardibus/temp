package com.kardibus.temp.dao

import com.kardibus.temp.model.Beer
import com.kardibus.temp.repository.BeerRepository
import org.springframework.stereotype.Component

@Component
class BeerDao(private var beerRepository: BeerRepository) {

    fun saveBeerModel(beer: Beer) {
        beerRepository.save(beer)
    }
}
