package com.kardibus.temp.repository

import com.kardibus.temp.model.programbeer.Step
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface StepRepository : JpaRepository<Step, UUID> {


}
