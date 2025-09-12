package com.kardibus.temp.controller

import com.kardibus.temp.service.StepService
import java.util.UUID
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("step/v1")
class StepController(private val stepService: StepService) {

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteStep(@PathVariable id: UUID) {
        println(id)
        stepService.deleteStepById(id)
    }
}
