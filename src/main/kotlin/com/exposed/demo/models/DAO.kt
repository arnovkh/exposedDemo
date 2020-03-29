package com.exposed.demo.models

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.EntityID

class Department(id: EntityID<Int>) : Entity<Int>(id) {
    companion object : EntityClass<Int, Department>(Departments)
    var name     by Departments.name
}

class Employee(id: EntityID<Int>) : Entity<Int>(id) {
    companion object : EntityClass<Int, Employee>(Employees)
    var employerRef by Employees.employerRef
    var name     by Employees.name
    var jobRole by Employees.jobRole
    var department by Employees.department
}