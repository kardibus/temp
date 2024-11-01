package com.kardibus.temp.service

import com.kardibus.temp.dto.DataDto
import com.kardibus.temp.model.brewery.Data
import com.kardibus.temp.repository.DataRepository
import java.time.LocalDateTime
import org.springframework.stereotype.Service

@Service
class DataService(private val dataRepository: DataRepository) {

    fun saveData(data: DataDto) {
        dataRepository.save(Data().apply {
            temp = data.temp
        })
    }
}