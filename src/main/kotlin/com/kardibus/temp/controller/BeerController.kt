package com.kardibus.temp.controller

import com.kardibus.temp.model.programbeer.Program
import com.kardibus.temp.model.programbeer.Step
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
class BeerController(private val map: Map<Program, List<Step>>) {

    @GetMapping
    fun getAllProgram(): Map<Program, List<Step>> {
        return map
    }

    @PostMapping
    fun createProgram(@RequestBody map:Map<Program, List<Step>>) {

    }

    @PutMapping
    fun updateProgram(@RequestBody map:Map<Program, List<Step>>) {

    }

    @DeleteMapping("/{id}")
    fun deleteProgram(@PathVariable("id") id:Long) {

    }
}