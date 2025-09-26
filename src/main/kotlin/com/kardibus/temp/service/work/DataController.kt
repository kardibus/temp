package com.kardibus.temp.service.work

import com.kardibus.temp.dto.DataDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/** Принимаем температуру с датчика микроконтроллера */
@RestController
@RequestMapping("data/v1")
class DataController(private val dataService: DataService) {
    @PostMapping
    fun data(
        @RequestBody data: DataDto,
    ) {
        dataService.saveData(data)
    }
}
