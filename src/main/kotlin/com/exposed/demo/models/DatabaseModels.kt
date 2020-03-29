package com.exposed.demo.models

import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Table


object Departments : IntIdTable() {
    val name = varchar("name", 50).uniqueIndex()
}

object Employees : IntIdTable() {
    val employeeRef = varchar("employee_ref",50).uniqueIndex()
    val name = varchar("name", 100)
    val jobRole = varchar("role", 50)
    val departmentId = integer("department_id").uniqueIndex().references(Departments.id)
}

