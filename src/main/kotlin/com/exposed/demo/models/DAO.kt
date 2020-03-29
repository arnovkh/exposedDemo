package com.exposed.demo.models

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.EntityID

data class Department(val id:Int = 0, val name: String)


data class Employee(
        val id: Int = 0,
    val employeeRef: String,
        val name: String,
        val jobRole: String,
    val department: Department
)

data class EmployeePerDepartment(  val department: Department, val countOfEmployees: Int)