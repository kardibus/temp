package com.kardibus.temp.service.step

import java.util.UUID
import com.kardibus.model.programbeer.Step
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface StepRepository : CrudRepository<Step, UUID> {
    @Modifying
    @Query("""delete from Step s where s.id = :id""")
    fun deleteStepById(id: UUID)
}
