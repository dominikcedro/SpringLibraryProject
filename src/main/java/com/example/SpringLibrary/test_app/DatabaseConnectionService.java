package com.example.SpringLibrary.test_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;

@Service
public class DatabaseConnectionService {

    private final DataSource dataSource;

    @Autowired
    public DatabaseConnectionService(DataSource dataSource) {
        this.dataSource = dataSource;
        checkConnection();
    }

    public void checkConnection() {
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("Successfully connected to the database.");
        } catch (Exception e) {
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
        }
    }
}