package com.kardibus.temp.repository

import com.kardibus.temp.model.brewery.Beer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BeerRepository : JpaRepository<Beer, Long>
