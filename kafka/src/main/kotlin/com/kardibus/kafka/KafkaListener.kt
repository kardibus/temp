package com.kardibus.kafka

import org.springframework.context.annotation.Profile
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
@Profile("PROD")
class KafkaListener : Consumer {

    @KafkaListener(id = "listen1", topics = ["my-topic"])
    override fun listener(message: String) {
        println(message)
    }
}
