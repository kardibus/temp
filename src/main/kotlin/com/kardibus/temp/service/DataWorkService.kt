package com.kardibus.temp.service

import com.kardibus.temp.dto.DataWorkDto
import com.kardibus.temp.repository.DataWorkRepository
import java.util.UUID
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class DataWorkService(private val dataWorkRepository: DataWorkRepository) {

    fun getDataWork(id: UUID): DataWorkDto? {
        return dataWorkRepository.findDataWorkById(id)
    }
}