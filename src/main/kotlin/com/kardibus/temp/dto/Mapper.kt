package com.kardibus.temp.dto

interface Mapper<E, E2, D> {
    fun to(entity: List<E>, entityTwo: E2): D
    fun from(domain: D): List<E>
    fun fromTwo(domain: D): E2
}
