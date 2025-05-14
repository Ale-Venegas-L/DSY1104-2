package com.educ.EduTech.controller;

import com.educ.EduTech.model.user;
import com.educ.EduTech.repository.UserRepo;
import com.educ.EduTech.services.userService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<user> guardarUser(@RequestBody user usr) {
        usr = new user();
        usr = userService.saveUser(usr);
        return ResponseEntity.status(HttpStatus.CREATED).body(usr);
    }

    @GetMapping("/{username}")
    public ResponseEntity<user> buscarUser(@PathVariable String usrname){
        try {
            user usr = userService.findByUser(usrname);
            return ResponseEntity.ok(usr);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<user> actualizar(@PathVariable String usrname, @RequestBody user usrs){
        try {
            user usr = userService.findByUser(usrname);
            usr.setUsername(usrname);
            usr.setPassword(usrs.getPassword());
            usr.setEmail(usrs.getEmail());
            usr.setTelefono(usrs.getTelefono());

            userService.saveUser(usr);
            return ResponseEntity.ok(usrs);

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(("/{username}"))
    public ResponseEntity<?> eliminar(@PathVariable String usrname){
        try {
            userService.deleteUser(usrname);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
}
