package com.exposed.demo.repository

import com.exposed.demo.configuration.DataSourceConfiguration
import com.exposed.demo.models.Department
import com.exposed.demo.models.Departments
import com.exposed.demo.models.Departments.id
import com.exposed.demo.models.Departments.name
import com.exposed.demo.models.Employees
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class DataRepository @Autowired constructor(private val dataSource: DataSourceConfiguration) {
    init {
        Database.connect(
                dataSource.jdbc_url, driver = "org.h2.Driver",
                user = dataSource.username)
        createDatabase()
        initData()
    }

    private fun createDatabase() {
        transaction {
            SchemaUtils.create(Departments, Employees)
            commit()
        }
    }

    private  fun initData() {
        transaction {
            if (Departments.selectAll().count() == 0) {
                Departments.insert { it[name] = "Human Resources" }
                Departments.insert { it[name] = "Information Technology" }
                Departments.insert { it[name] = "Accounting" }
                commit()
            }
        }
    }

    fun getDepartments(): List<Department> {
        return transaction {
             Departments.selectAll().map {
                 Department(it[name], it[id].value)
             }
        }
    }

    fun createDepartment(department: Department): Department {
        return transaction {
        val id =    Departments.insertAndGetId {
                it[name] = department.name
            }
        department.copy(id= id.value)
        }
    }

    fun getDepartmentById(idValue: Int): Department? {
        return transaction {
            Departments.select { Departments.id.eq(idValue)}.map {
                Department(it[name], it[id].value)
            }.firstOrNull()
        }
    }


}