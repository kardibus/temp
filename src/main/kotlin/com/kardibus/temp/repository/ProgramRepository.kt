package com.kardibus.temp.repository

import com.kardibus.temp.model.programbeer.Program
import java.util.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ProgramRepository : JpaRepository<Program, UUID> {

    @Query(
        """ 
        select p from Program p 
            join fetch p.steps s
            where p.id = :id
    """
    )
    fun findProgramById(id: UUID): Program
}