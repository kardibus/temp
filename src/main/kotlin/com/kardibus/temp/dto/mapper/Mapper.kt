package com.kardibus.temp.dto.mapper

interface Mapper<E, A, D> {

    fun toProgramDto(entity: List<E>, entityTwo: A): D

    fun fromStep(domain: D): List<E>

    fun fromProgram(domain: D): A
}
