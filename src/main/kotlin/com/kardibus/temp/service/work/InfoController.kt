package com.kardibus.temp.service.work

import com.kardibus.actionevent.annotation.ActionEvent
import com.kardibus.model.programbeer.Work
import com.kardibus.temp.utils.common.mapNameToLabel
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("info/v1")
class InfoController(private val infoService: InfoService) {
    @GetMapping
    @ActionEvent
    fun info(): Map<String, List<String>> {
        return infoService.handlerMapping()
    }

    @GetMapping("work")
    fun work(): Map<String, String> = mapNameToLabel<Work>()
}
