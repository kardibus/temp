package com.kardibus.temp.config

import com.kardibus.temp.model.programbeer.Program
import com.kardibus.temp.model.programbeer.Step
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CommonConfig {

    @Bean
    fun programStep(): Map<Program, List<Step>> {
        return mapOf(
            Program().apply {
                id = 0
                name = "beer"
                work = false
            }
                to listOf(
                    Step().apply {
                        id = 0
                        step = 1
                        time = 10
                    },
                    Step().apply {
                        id = 1
                        step = 2
                        time = 20
                    },
                    Step().apply {
                        id = 2
                        step = 3
                        time = 40
                    }
                )
        )
    }
}
