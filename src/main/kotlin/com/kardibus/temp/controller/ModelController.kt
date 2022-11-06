package com.kardibus.temp.controller

import com.kardibus.temp.model.Model
import com.kardibus.temp.repository.ModelRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

/** Контроллер для микроконтроллера */
@RestController
class ModelController(private var modelRepository: ModelRepository) {
    @GetMapping
    fun getModel(): Optional<Model> {
        return modelRepository.findByProg()
    }
}
