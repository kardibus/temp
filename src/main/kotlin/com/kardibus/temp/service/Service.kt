package com.kardibus.temp.service

import com.kardibus.temp.model.Model
import com.kardibus.temp.repository.ModelRepository

@org.springframework.stereotype.Service
class Service(private val modelRepository: ModelRepository) {

    fun getOneFirstModel(): Model {
        return modelRepository.findAll().first()
    }
}
