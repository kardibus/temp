package com.kardibus.temp.service.work

import java.util.UUID
import com.kardibus.model.brewery.Data
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.ListCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DataRepository : JpaRepository<Data, UUID>, ListCrudRepository<Data, UUID>
