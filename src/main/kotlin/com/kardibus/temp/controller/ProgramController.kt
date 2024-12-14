package com.kardibus.temp.controller

import com.kardibus.temp.dto.ProgramDto
import com.kardibus.temp.service.ProgramService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/program")
class ProgramController(private val programService: ProgramService) {

    @GetMapping
    fun getProgram(): List<ProgramDto> = programService.getPrograms()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addProgram(@RequestBody programDto: ProgramDto) = programService.saveProgram(programDto)
}