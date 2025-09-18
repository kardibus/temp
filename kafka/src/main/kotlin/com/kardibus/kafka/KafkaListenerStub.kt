package com.kardibus.kafka

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile("DEV")
class KafkaListenerStub : Consumer {
    override fun listener(message: String) {
        LoggerFactory.getLogger("KafkaListenerStub").warn("Kafka работает в режиме заглушки")
    }
}
