package com.kardibus.temp.controller

import com.kardibus.temp.dto.ProgramDto
import com.kardibus.temp.service.ProgramService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("v1/program")
class ProgramController(private val programService: ProgramService) {

    @GetMapping("/")
    fun getUser(model: MutableMap<String, List<ProgramDto>>): String {
        model.put("Programs", programService.getPrograms())
        return "program"
    }
}
