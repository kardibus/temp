package com.kardibus.temp.kafka

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class Listener {

    @KafkaListener(id = "listen1", topics = ["my-topic"])
    fun listen1(`in`: String) {
        println(`in`)
    }
}
