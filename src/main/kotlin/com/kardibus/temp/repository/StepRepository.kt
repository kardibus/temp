package com.kardibus.temp.repository

import com.kardibus.temp.model.programbeer.Step
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

@org.springframework.stereotype.Repository
interface StepRepository : JpaRepository<Step, Long> {

    @Query("select s from Step s where s.prog_id = :id order by s.step")
    fun findByProg_id(id: Long): List<Step>

    @Query("select s.id, s.step, s.time,s.done, s.prog_id, s.from_date, s.to_date, s.temp from Step s where s.prog_id = :id and s.done = false order by s.step asc limit 1", nativeQuery = true)
    fun findByProg_idNotDone(id: Long): List<Step>

    @Query("select count(s) from Step s where s.prog_id = :id", nativeQuery = true)
    fun findByCountStep(id: Long): Long
}
