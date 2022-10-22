package com.kardibus.temp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableJpaRepositories
@EnableScheduling
class TempApplication

fun main(args: Array<String>) {
    runApplication<TempApplication>(*args)
}
