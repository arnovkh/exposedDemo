package com.exposed.demo.models

import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Table


object Departments : IntIdTable() {
    val name = varchar("name", 50).uniqueIndex()
}

object Employees : IntIdTable() {
    val employeeRef = integer("employee_ref").uniqueIndex()
    val name = varchar("name", 50)
    val jobRole = varchar("role", 50)
    val department = reference("dept_id", Departments.id).uniqueIndex()
}

