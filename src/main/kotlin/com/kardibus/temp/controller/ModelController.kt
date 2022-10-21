package com.kardibus.temp.controller

import com.kardibus.temp.dao.ModelDao
import com.kardibus.temp.dto.ModelDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ModelController(private var modelDao: ModelDao) {
    @GetMapping
    fun getModel(): ModelDto {
        return modelDao.getModel()
    }
}
