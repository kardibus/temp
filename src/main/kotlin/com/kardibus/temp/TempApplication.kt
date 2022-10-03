package com.kardibus.temp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TempApplication

fun main(args: Array<String>) {
    runApplication<TempApplication>(*args)
}
