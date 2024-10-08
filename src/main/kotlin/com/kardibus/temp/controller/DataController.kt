package com.kardibus.temp.controller

import com.kardibus.temp.dto.DataDto
import com.kardibus.temp.service.DataService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/** Принимаем температуру с датчика микроконтроллера */
@RestController("data")
class DataController(private val dataService: DataService) {

    @PostMapping
    fun data(@RequestBody data: DataDto) {
        dataService.saveData(data)
    }
}