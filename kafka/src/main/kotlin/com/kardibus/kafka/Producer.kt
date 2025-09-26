package com.kardibus.kafka

interface Producer {

    fun send(message: String)
}
