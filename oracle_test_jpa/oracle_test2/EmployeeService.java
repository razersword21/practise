package com.example.oracle_test2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DatabaseConfigService databaseConfigService;

    private void checkDatabaseConfiguration() {
        if (!databaseConfigService.isDatabaseConfigured()) {
            throw new IllegalStateException("Database is not configured. Please set up the username and password first.");
        }
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employee.setName(employeeDetails.getName());
//        employee.setPosition(employeeDetails.getPosition());
        employee.setSalary(employeeDetails.getSalary());
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public List<Employee> searchEmployees(String name, Double minSalary, Double maxSalary) {
        if (name != null) {
            return employeeRepository.findByName(name);
        }
        return employeeRepository.findBySalaryBetween(minSalary, maxSalary);
    }
}
