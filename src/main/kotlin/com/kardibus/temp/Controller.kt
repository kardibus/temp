package com.kardibus.temp

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller {

    private var list: MutableList<Model> = ArrayList()

    @GetMapping
    fun get(): MutableList<Model> {
        return list
    }

    @PostMapping
    fun set(@RequestBody mod: Model) {
        list.add(mod)
    }
}