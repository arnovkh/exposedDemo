package com.exposed.demo.service

import com.exposed.demo.models.Employee
import com.exposed.demo.repository.DataRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EmployeeService @Autowired constructor(private val dataRepository: DataRepository) {

    fun getAllEmployees(): List<Employee> {
        return dataRepository.getEmployeesWithDepartments()
    }

    fun createEmployee(employee: Employee): Employee {
        return dataRepository.createEmployee(employee)
    }

    fun getEmployeeById(id: Int): Employee? {
        return dataRepository.getEmployeeById(id)
    }

    fun deleteEmployee(id: Int) {
        dataRepository.deleteEmployee(id)
    }

    fun updateEmployee(employee: Employee): Employee {
        return dataRepository.updateEmployee(employee)
    }
}