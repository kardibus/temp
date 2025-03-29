package com.kardibus.temp.utils.common

import org.springframework.data.repository.CrudRepository

inline fun <reified T, ID : Any> CrudRepository<T, ID>.findByIdOrThrow(id: ID): T =
    findById(id).orElseThrow { NoSuchElementException("Не найдент ${T::class.java.simpleName} по айди $id") }
