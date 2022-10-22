package com.kardibus.temp.repository

import com.kardibus.temp.model.programbeer.Step
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

@org.springframework.stereotype.Repository
interface StepRepository : JpaRepository<Step, Long> {

    @Query("""select s from Step s where s.prog_id = :id""")
    fun findByProg_id(id: Long): List<Step>
}
