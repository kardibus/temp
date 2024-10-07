package com.kardibus.temp.dto.mapper

interface MapperData<E, D> {

    fun toProgram(entity: E): D

    fun fromProgram(domain: D): E
}
