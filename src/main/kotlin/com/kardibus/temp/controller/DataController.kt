package com.kardibus.temp.controller

import com.kardibus.temp.dto.DataDto
import com.kardibus.temp.service.DataService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/** Принимаем температуру с датчика микроконтроллера */
@RestController
@RequestMapping("/data")
class DataController(private val dataService: DataService) {
    @PostMapping("/v1")
    fun data(
        @RequestBody data: DataDto,
    ) {
        dataService.saveData(data)
    }
}
