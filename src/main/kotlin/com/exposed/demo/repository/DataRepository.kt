package com.exposed.demo.repository

import com.exposed.demo.models.Departments
import com.exposed.demo.models.Employees
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.sql.DataSource

@Component
class DataRepository @Autowired constructor(val dataSource: DataSource) {
    init {
        Database.connect(dataSource)
        createDatabase()

    }

    fun createDatabase() {
        transaction {
            SchemaUtils.create(Departments, Employees)
            //Do stuff
        }
    }

    fun initData() {
        if(Departments.selectAll().count() == 0) {
            transaction {
                Departments.insert { it[name] = "Human Resources" }
                Departments.insert { it[name] = "Information Technology" }
                Departments.insert { it[name] = "Accounting" }
            }
        }
    }


}