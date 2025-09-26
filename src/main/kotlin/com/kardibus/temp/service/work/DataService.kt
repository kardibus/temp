package com.kardibus.temp.service.work

import com.kardibus.temp.dto.DataDto
import com.kardibus.model.brewery.Data
import org.springframework.stereotype.Service

@Service
class DataService(private val dataRepository: DataRepository) {
    fun saveData(data: DataDto) {
        dataRepository.save(
            Data().apply {
                temp = data.temp
            },
        )
    }
}
