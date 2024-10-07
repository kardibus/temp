package com.kardibus.temp.dto.mapper

interface Mapper<E, A, D> {

    fun toProgramDto(entity: List<E>, entityTwo: A): D

    fun fromProgram(domain: D): A
}
