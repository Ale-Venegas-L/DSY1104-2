package com.duoc.edutech.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.duoc.edutech.model.User;
import com.duoc.edutech.service.UserService;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @GetMapping("/users")
    public List<User> fetchUsers(){
        return userService.fetchUsers();
    }

    @PutMapping("/users/{username}")
    public User updateUser(@RequestBody User user, @PathVariable String username){
        return userService.updateUser(user, username);
    }

    @DeleteMapping("/users/{username}")
    public String deleteUser(@PathVariable String username){
        userService.deleteUser(username);
        return "Usuario eliminado";
    }

}
