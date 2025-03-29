package com.kardibus.temp.repository

import com.kardibus.temp.model.brewery.Data
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.ListCrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface DataRepository : CrudRepository<Data, UUID>, ListCrudRepository<Data, UUID>
