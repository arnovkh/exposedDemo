package com.exposed.demo.controller

import com.exposed.demo.models.Department
import com.exposed.demo.service.DepartmentService
import io.swagger.models.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController("/department")
class DepartmentController @Autowired constructor(val departmentService: DepartmentService) {
    @GetMapping
    fun getAllDepartments(): ResponseEntity<List<Department>> {
        return ResponseEntity.ok(departmentService.getAllDepartment())
    }

    @GetMapping("/{id}")
    fun getDepartmentById(@PathVariable id: Int): ResponseEntity<*> {
        return try {
             ResponseEntity.ok(departmentService.findDepartmentById(id = id)!!)
        } catch(e: Exception) {
             ResponseEntity.badRequest().body("Error occurred with provided id")
        }
    }

    @PostMapping
    fun createDepartment(@RequestBody department: Department ): ResponseEntity<Department> {
        return ResponseEntity.ok(departmentService.createDepartment(department = department ))
    }
}
