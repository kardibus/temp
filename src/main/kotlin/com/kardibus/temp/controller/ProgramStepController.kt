package com.kardibus.temp.controller

import com.kardibus.temp.dao.ProgramService
import com.kardibus.temp.dto.ProgramDto
import com.kardibus.temp.dto.WorkDto
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/beer")
class ProgramStepController(private val programService: ProgramService) {

    @GetMapping
    fun getAllProgram(): ProgramDto {
        return programService.getAllProgram().first()
    }

    @GetMapping("/{id}")
    fun getByIdProgram(@PathVariable("id") id: Long): List<ProgramDto> {
        return programService.getFindByIdProgram(id)
    }

    @PostMapping
    fun createProgram(@RequestBody map: List<ProgramDto>) {
        programService.createProgram(map)
    }

    @PutMapping
    fun updateProgram(@RequestBody map: List<ProgramDto>) {
        programService.updateProgram(map)
    }

    @DeleteMapping("/{id}")
    fun deleteProgram(@PathVariable("id") id: Long) {
        programService.deleteProgram(id = id)
    }

    @PostMapping("/work")
    fun changeWork(@RequestBody workDto: WorkDto) {
        programService.changeWork(workDto.work)
    }
}
