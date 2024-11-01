package com.kardibus.temp.repository

import com.kardibus.temp.dto.DataDto
import com.kardibus.temp.dto.DataWorkDto
import com.kardibus.temp.model.programbeer.Program
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface DataWorkRepository : JpaRepository<Program, UUID> {

    @Query("""
        select new com.kardibus.temp.dto.DataWorkDto(
                p.id,
                count(s),
                s.step,
                s.temp,
                p.work
               )
            from Program p
            join p.steps s
        where p.id = :id and s.work = true and s.done = false
        group by p.id
    """)
    fun findDataWorkById(id: UUID): DataWorkDto?
}
