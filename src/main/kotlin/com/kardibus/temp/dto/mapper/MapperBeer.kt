package com.kardibus.temp.dto.mapper

interface MapperBeer<E, D> {

    fun toProgram(entity: E): D

    fun fromProgram(domain: D): E
}
