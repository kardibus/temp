package com.kardibus.temp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication(scanBasePackages = ["com.kardibus.*"])
@EnableJpaRepositories(basePackages = ["com.kardibus.*"])
@EntityScan(basePackages = ["model.*"])
class TempApplication

fun main(args: Array<String>) {
    runApplication<TempApplication>(*args)
}
