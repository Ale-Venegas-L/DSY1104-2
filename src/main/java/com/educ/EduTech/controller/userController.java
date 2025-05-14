package com.educ.EduTech.controller;

import com.educ.EduTech.model.user;
import com.educ.EduTech.repository.UserRepo;
import com.educ.EduTech.services.userService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1/user")
public class userController {
    @Autowired
    private userService usrServ;

    @GetMapping
    public ResponseEntity<List<user>> listar(){
        List<user> users = userService.findAll();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }
    
    @PostMapping
    public ResponseEntity<user> buscarUser(@RequestBody user usr) {
        user user = userService.saveUser(usr);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("username")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
}
