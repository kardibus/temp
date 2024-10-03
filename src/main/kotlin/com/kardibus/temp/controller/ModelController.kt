package com.kardibus.temp.controller

import com.kardibus.temp.model.brewery.DataWork
import com.kardibus.temp.repository.ModelRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

/** Контроллер для микроконтроллера */
@RestController
class ModelController(private var modelRepository: ModelRepository) {
    @GetMapping
    fun getModel(): Optional<DataWork> {
        return modelRepository.findByProg()
    }
}
