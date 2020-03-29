package com.exposed.demo.repository

import com.exposed.demo.configuration.DataSourceConfiguration
import com.exposed.demo.models.Department
import com.exposed.demo.models.Departments
import com.exposed.demo.models.Departments.id
import com.exposed.demo.models.Departments.name
import com.exposed.demo.models.Employee
import com.exposed.demo.models.Employees
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
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
    /*** region: Departments **/

    fun getDepartments(): List<Department> {
        return transaction {
             Departments.selectAll().map {
                 Department(it[id].value, it[name])
             }
        }
    }

    fun createDepartment(department: Department): Department {
        return transaction {
        val id =    Departments.insertAndGetId {
                it[name] = department.name
            }
            commit()
        department.copy(id= id.value)
        }
    }

    fun getDepartmentById(idValue: Int): Department? {
        return transaction {
            Departments.slice(Departments.id, Departments.name).select { Departments.id eq idValue}.map {
                Department( it[id].value, it[name])
            }.firstOrNull()
        }
    }

    /***endregion***/

    /*** region: Employees **/

    fun createEmployee(employee: Employee): Employee {
        return transaction {
            val id = Employees.insertAndGetId {
                it[name] = employee.name
                it[employeeRef] = employee.employeeRef
                it[departmentId] =  employee.department.id
                it[jobRole] = employee.jobRole
            }
            commit()
            employee.copy(id = id.value)
        }
    }

    // example with join
    fun getEmployeesWithDepartments() : List<Employee> {
        return transaction {
            (Employees innerJoin Departments).selectAll().map {
                Employee(id = it[Employees.id].value,
                        employeeRef = it[Employees.employeeRef] ,
                        jobRole = it[Employees.jobRole],
                        department = Department(name = it[Departments.name], id=it[Departments.id].value),
                        name = it[Employees.name]
                )
            }
        }
    }

    fun getEmployeeById(idValue: Int): Employee? {
        return transaction {
            (Employees innerJoin Departments).select{Employees.id eq idValue}.map {
                Employee(id = it[Employees.id].value,
                        employeeRef = it[Employees.employeeRef] ,
                        jobRole = it[Employees.jobRole],
                        department = Department(name = it[Departments.name], id=it[Departments.id].value),
                        name = it[Employees.name]
                )
            }.firstOrNull()
        }
    }

    /**
     * Equivalent to UPDATE EMPLOYEES  SET .... WHERE EMPLOYEE_ID = ?
     */
    fun updateEmployee(employee: Employee): Employee {
        return transaction {
            Employees.update({
                Employees.id eq employee.id
            }) {
                it[name] = employee.name
                it[jobRole] = employee.jobRole
                it[departmentId] =  employee.department.id
            }
            employee
        }
    }

    fun deleteEmployee(employeeId: Int) {
        return transaction {
            Employees.deleteWhere { Employees.id eq employeeId }
        }
    }

    /***endregion***/

}
