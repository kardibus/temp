package com.kardibus.temp.dto.mapper

import com.kardibus.temp.dto.DataDto
import com.kardibus.temp.model.brewery.Data

class MapperDataImp : MapperData<Data, DataDto> {

    override fun toProgram(entity: Data): DataDto {
        return DataDto(entity.temp)
    }

    override fun fromProgram(domain: DataDto): Data {
        return Data().apply {
            temp = domain.temp
        }
    }
}