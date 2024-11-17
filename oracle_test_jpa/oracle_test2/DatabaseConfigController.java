package com.example.oracle_test2;

import com.example.oracle_test2.DatabaseConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/database")
public class DatabaseConfigController {

    @Autowired
    private DatabaseConfigService databaseConfigService;

    @PostMapping("/configure")
    public String configureDatabase(@RequestParam(name = "username") String username, @RequestParam String password) {
        System.out.print("first user"+username+"pswd"+password);
        databaseConfigService.configureDatabase(username, password);
        return "Database configured successfully.";
    }
}
