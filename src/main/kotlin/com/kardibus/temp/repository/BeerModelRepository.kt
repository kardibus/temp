package com.kardibus.temp.repository

import com.kardibus.temp.model.Beer
import org.springframework.data.jpa.repository.JpaRepository

@org.springframework.stereotype.Repository
interface BeerModelRepository:JpaRepository<Beer,Long>