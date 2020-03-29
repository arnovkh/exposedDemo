package com.exposed.demo.models

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.EntityID

data class Department(val name: String, val id:Int = 0)


data class Employee(
        val id: Int =0,
        val employeeId: Int,
    val employeeRef: String,
        val name: String,
        val jobRole: String,
    val department: Department
)