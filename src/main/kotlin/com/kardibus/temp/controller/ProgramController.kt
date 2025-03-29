package com.kardibus.temp.controller

import com.kardibus.temp.dto.ProgramDto
import com.kardibus.temp.model.programbeer.Work
import com.kardibus.temp.service.ProgramService
import com.kardibus.temp.utils.common.mapNameToLabel
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/program")
class ProgramController(private val programService: ProgramService) {
    @GetMapping
    fun getProgram(): List<ProgramDto> = programService.getPrograms()

    @GetMapping("getWork")
    fun getWork(): Map<String, String> = mapNameToLabel<Work>()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addProgram(
        @RequestBody programDto: ProgramDto,
    ) = programService.saveProgram(programDto)
}
