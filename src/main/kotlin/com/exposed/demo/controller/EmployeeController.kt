package com.exposed.demo.controller

import com.exposed.demo.models.Employee
import com.exposed.demo.service.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.Exception
import java.net.URI

@RestController()
@RequestMapping("/employees")
@CrossOrigin(origins = ["*"])
class EmployeeController @Autowired constructor(private val employeeService: EmployeeService){

    @GetMapping
    fun getAllEmployees(): ResponseEntity<List<Employee>> {
        return ResponseEntity.ok(employeeService.getAllEmployees())
    }

    @GetMapping("/{id}")
    fun getEmployeeById(@PathVariable id: Int): ResponseEntity<*> {
        return try {
         ResponseEntity.ok(employeeService.getEmployeeById(id)!!)
        } catch(e: Exception) {
            ResponseEntity.badRequest().body(e.toString())
        }
    }

    @PostMapping
    fun createEmployee(@RequestBody employee: Employee): ResponseEntity<Employee> {
        val created = employeeService.createEmployee(employee)
        return ResponseEntity.created(URI("/employees/${created.id}")).body(created)
    }

    @PutMapping
    fun updateEmployee(@RequestBody employee: Employee): ResponseEntity<Employee> {
        return ResponseEntity.ok(employeeService.updateEmployee(employee))
    }
}