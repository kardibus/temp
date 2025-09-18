package com.kardibus.kafka

interface Consumer {

    fun listener(message: String)
}
