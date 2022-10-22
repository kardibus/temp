package com.kardibus.temp.controller

import com.kardibus.temp.dao.ModelDao
import com.kardibus.temp.dto.ModelDto
import com.kardibus.temp.model.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Optional

@RestController
class ModelController(private var modelDao: ModelDao) {
    @GetMapping
    fun getModel(): Optional<Model> {
        return modelDao.getModel()
    }
}
