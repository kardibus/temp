package com.kardibus.temp.dao

import com.kardibus.temp.dto.ModelDto
import com.kardibus.temp.dto.mapper.MapperModelImp
import com.kardibus.temp.repository.ModelRepository
import org.springframework.stereotype.Component

/** @param getModel Микроконтроллер ожидает получить только один объект */

@Component
class ModelDao(private var modelRepository: ModelRepository) {

    fun getModel(): ModelDto {
        return MapperModelImp().toModel(modelRepository.findAll().first())
    }
}
