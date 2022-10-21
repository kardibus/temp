package com.kardibus.temp.dto.mapper

interface MapperModel<E, D> {

    fun toModel(entity: E): D

    fun fromModel(domain: D): E
}
