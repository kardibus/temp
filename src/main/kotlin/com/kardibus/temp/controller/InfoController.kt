package com.kardibus.temp.controller

import com.kardibus.temp.actionevent.annotation.ActionEvent
import com.kardibus.temp.service.InfoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/info")
class InfoController(private val infoService: InfoService) {
    @GetMapping
    @ActionEvent
    fun info(): Map<String, List<String>> {
        return infoService.handlerMapping()
    }
}
