package com.example.oracle_test4;

import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private Connection conn;

    // 連接資料庫
    public String connect(String username, String password) {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        try {
            conn = DriverManager.getConnection(url, username, password);
            return "Connected to Oracle database.";
        } catch (SQLException e) {
            return "Connection failed: " + e.getMessage();
        }
    }

    // 斷開資料庫連線
    public String disconnect() {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
                return "Disconnected from Oracle database.";
            } catch (SQLException e) {
                return "Error disconnecting: " + e.getMessage();
            }
        } else {
            return "No active connection to disconnect.";
        }
    }

    // 創建員工資料
    public String createEmployee(String name, double salary) {
        String sql = "INSERT INTO employees (name, salary) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, salary);
            pstmt.executeUpdate();
            return "Employee created.";
        } catch (SQLException e) {
            return "Error creating employee: " + e.getMessage();
        }
    }

    // 讀取員工資料
    public List<Employee> getEmployees() throws SQLException {
        String sql = "SELECT * FROM employees";
        List<Employee> employees = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                employees.add(new Employee(rs.getInt("id"), rs.getString("name"), rs.getDouble("salary")));
            }
        }
        return employees;
    }

    // 更新員工資料
    public String updateEmployee(int id, double salary) {
        String sql = "UPDATE employees SET salary = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, salary);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            return "Employee updated.";
        } catch (SQLException e) {
            return "Error updating employee: " + e.getMessage();
        }
    }

    // 刪除員工資料
    public String deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return "Employee deleted.";
        } catch (SQLException e) {
            return "Error deleting employee: " + e.getMessage();
        }
    }
}
