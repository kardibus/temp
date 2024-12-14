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
        select p from UserBrewery u
            join u.programs p
            join fetch p.steps s
            where u.id = :id
            order by p.id, s.id, s.step
    """
    )
    fun findProgramByUserId(id: UUID): Program

    @Query(value =
        """
        select p from Program p
            join fetch p.steps s
            where p.id = :id and s.work = false and s.done = false
            order by p.id, s.id, s.step
    """
    )
    fun findAllByStepWorkTrue(id: UUID): Program

    @Query(
        """
        select p from Program p
            join fetch p.steps s
            where p.id = :id and s.done = false
            order by p.id, s.id, s.step
    """
    )
    fun findAllByStepDoneFalse(id: UUID): Program
}