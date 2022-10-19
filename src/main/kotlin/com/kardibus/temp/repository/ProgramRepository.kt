package com.kardibus.temp.repository

import com.kardibus.temp.model.programbeer.Program
import org.springframework.data.jpa.repository.JpaRepository

@org.springframework.stereotype.Repository
interface ProgramRepository : JpaRepository<Program, Long>
