package com.kardibus.temp.repository

import com.kardibus.temp.model.programbeer.Step
import org.springframework.data.jpa.repository.JpaRepository

@org.springframework.stereotype.Repository
interface StepRepository : JpaRepository<Step, Long>
