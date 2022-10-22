package com.kardibus.temp.controller

import com.kardibus.temp.dao.ProgramDao
import com.kardibus.temp.dto.ProgramDto
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
class ProgramStepController(private val programDao: ProgramDao) {

    @GetMapping
    fun getAllProgram(): List<ProgramDto> {
        return programDao.getAllProgram()
    }

    @GetMapping("/{id}")
    fun getByIdProgram(@PathVariable("id") id: Long): List<ProgramDto> {
        return programDao.getFindByIdProgram(id)
    }

    @PostMapping
    fun createProgram(@RequestBody map: List<ProgramDto>) {
        programDao.createProgram(map)
    }

    @PutMapping
    fun updateProgram(@RequestBody map: List<ProgramDto>) {
        programDao.updateProgram(map)
    }

    @DeleteMapping("/{id}")
    fun deleteProgram(@PathVariable("id") id: Long) {
        programDao.deleteProgram(id = id)
    }
}
