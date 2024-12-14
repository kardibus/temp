package com.kardibus.temp.controller

import com.kardibus.temp.dto.DataWorkDto
import com.kardibus.temp.service.DataWorkService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

/** Отдаем микроконтроллеру готовую программу */
@RestController
@RequestMapping("/v1/work")
class DataWorkController(private val dataWorkService: DataWorkService) {
    @GetMapping("{id}")
    fun dataWork(
        @PathVariable id: UUID,
    ): DataWorkDto {
        return dataWorkService.getDataWorkForUser(id)
    }
}
