package com.kardibus.temp.dao

import com.kardibus.temp.model.Model
import com.kardibus.temp.repository.ModelRepository
import org.springframework.stereotype.Component
import java.util.*

/** @param getModel Микроконтроллер ожидает получить только один объект */

@Component
class ModelDao(private var modelRepository: ModelRepository) {

    fun getModel(): Optional<Model> {
        return modelRepository.findByProg()
    }

    fun updeteModel(model: Model) {
        modelRepository.save(model)
    }

    fun getByIdModel(): Optional<Model> {
        return modelRepository.findByProg()
    }
}
