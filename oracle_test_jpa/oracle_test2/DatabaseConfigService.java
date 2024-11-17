package com.example.oracle_test2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Service
public class DatabaseConfigService {

    private DriverManagerDataSource dataSource;
    private boolean isConfigured = false;

    public String configureDatabase(String username, String password) {
        DriverManagerDataSource tempDataSource = new DriverManagerDataSource();
        tempDataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        tempDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        tempDataSource.setUsername(username);
        tempDataSource.setPassword(password);
        try (Connection connection = tempDataSource.getConnection()) {
            // 連線成功後設置資料來源
            this.dataSource = tempDataSource;
            this.isConfigured = true;
            return "Database connected successfully.";
        } catch (SQLException e) {
            return "Failed to connect to database: " + e.getMessage();
        }
    }

    public DataSource getDataSource() {
        if (!isConfigured) {
            throw new IllegalStateException("Database is not configured. Please set up the username and password first.");
        }
        return dataSource;
    }

    public boolean isDatabaseConfigured() {
        return isConfigured;
    }
}
