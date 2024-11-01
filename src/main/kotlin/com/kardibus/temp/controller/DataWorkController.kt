package com.kardibus.temp.controller

import com.kardibus.temp.dto.DataWorkDto
import com.kardibus.temp.service.DataWorkService
import java.util.UUID
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/** Отдаем микроконтроллеру готовую программу */
@RestController
@RequestMapping("/work")
class DataWorkController(private val dataWorkService: DataWorkService) {

    @GetMapping("/v1/{id}")
    fun dataWork(@PathVariable id: UUID): DataWorkDto? {
        return dataWorkService.getDataWork(id)
    }
}