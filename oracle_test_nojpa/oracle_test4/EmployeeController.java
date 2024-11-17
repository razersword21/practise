package com.example.oracle_test4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService databaseService;

    // 連接資料庫
    @PostMapping("/connect")
    public String connectToDatabase(@RequestParam("username") String username, @RequestParam("password") String password) {
        System.out.print(username+password);
        return databaseService.connect(username, password);
    }

    // 登出（斷開連線）
    @PostMapping("/disconnect")
    public String disconnectFromDatabase() {
        return databaseService.disconnect();
    }

    // 創建員工資料
    @PostMapping("/employees")
    public String createEmployee(@RequestParam String name, @RequestParam double salary) {
        return databaseService.createEmployee(name, salary);
    }

    // 讀取所有員工資料
    @GetMapping("/employees")
    public List<Employee> getEmployees() throws SQLException {
        return databaseService.getEmployees();
    }

    // 更新員工薪水
    @PutMapping("/employees/{id}")
    public String updateEmployee(@PathVariable int id, @RequestParam double salary) {
        return databaseService.updateEmployee(id, salary);
    }

    // 刪除員工
    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {
        return databaseService.deleteEmployee(id);
    }
}
