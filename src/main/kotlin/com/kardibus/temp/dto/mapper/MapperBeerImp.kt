package com.kardibus.temp.dto.mapper

import com.kardibus.temp.dto.BeerDto
import com.kardibus.temp.model.brewery.Beer

class MapperBeerImp : MapperBeer<Beer, BeerDto> {

    override fun toProgram(entity: Beer): BeerDto {
        return BeerDto().apply {
            id = entity.id
            temp = entity.temp
            date = entity.date
        }
    }

    override fun fromProgram(domain: BeerDto): Beer {
        return Beer().apply {
            id = domain.id
            temp = domain.temp
            date = domain.date
        }
    }
}
