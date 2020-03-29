package com.exposed.demo.service

import com.exposed.demo.models.Department
import com.exposed.demo.models.EmployeePerDepartment
import com.exposed.demo.repository.DataRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DepartmentService @Autowired constructor(val dataRepository: DataRepository) {

    fun getAllDepartment() : List<Department> {
        return dataRepository.getDepartments()
    }

    fun createDepartment(department: Department): Department {
        return dataRepository.createDepartment(department)
    }

    fun findDepartmentById(id: Int): Department? {
        return dataRepository.getDepartmentById(id)
    }

    fun getStatsPerDepartment(): List<EmployeePerDepartment> {
        return dataRepository.getEmployeesByDepartment()
    }
}