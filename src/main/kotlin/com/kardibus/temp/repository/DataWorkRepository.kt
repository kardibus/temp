package com.kardibus.temp.repository

import com.kardibus.temp.model.brewery.DataWork
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface DataWorkRepository : JpaRepository<DataWork, UUID> {
}
