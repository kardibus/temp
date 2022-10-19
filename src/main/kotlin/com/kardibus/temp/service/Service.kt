package com.kardibus.temp.service

import com.kardibus.temp.model.Model
import com.kardibus.temp.repository.Repository

@org.springframework.stereotype.Service
class Service(private val repository: Repository) {

    fun getOneFirstModel(): Model {
        return repository.findAll().first()
    }
}
