package com.kardibus.temp.service

import com.kardibus.temp.dao.ProgramDao
import org.springframework.stereotype.Service

@Service
class ProgramService(private var programDao: ProgramDao) {

    fun enableWork() {}

    fun timeWork() {}
}
