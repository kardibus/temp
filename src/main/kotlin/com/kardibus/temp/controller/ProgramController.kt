package com.kardibus.temp.controller

import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.stream.Collectors
import org.springframework.web.bind.annotation.GetMapping

@RestController
@RequestMapping("v1/program")
open class ProgramController {

    @GetMapping("/user")
    fun getUser(model: Model): String? {
        val user = User("John Doe", 30)
        model.addAttribute("user", user)
        return "user"
    }

    data class User(val name: String,val age: Int)
}
