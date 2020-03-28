package com.exposed.demo.repository

import org.jetbrains.exposed.sql.Database
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.sql.DataSource

@Component
class DataRepository @Autowired constructor(val dataSource: DataSource) {
    init {
        Database.connect(dataSource)
    }

    fun createDatabase() {

    }
}