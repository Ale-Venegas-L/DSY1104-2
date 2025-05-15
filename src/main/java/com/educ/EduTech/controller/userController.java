package com.educ.EduTech.controller;

import com.educ.EduTech.model.user;
import com.educ.EduTech.services.userService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/user")
public class userController {
    @Autowired
    private userService usrServ;

    @GetMapping
    public ResponseEntity<List<user>> listar(){
        List<user> users = usrServ.findAll();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }
    
    @PostMapping
    public ResponseEntity<user> guardarUser(@RequestBody user usr) {
        usr = new user();
        usr = usrServ.saveUser(usr);
        return ResponseEntity.status(HttpStatus.CREATED).body(usr);
    }

    @GetMapping("/{username}")
    public ResponseEntity<user> buscarUser(@PathVariable String usrname){
        try {
            user usr = usrServ.findByUser(usrname);
            return ResponseEntity.ok(usr);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<user> actualizar(@PathVariable String usrname, @RequestBody user usrs){
        try {
            user usr = usrServ.findByUser(usrname);
            usr.setUsername(usrname);
            usr.setPassword(usrs.getPassword());
            usr.setEmail(usrs.getEmail());
            usr.setTelefono(usrs.getTelefono());

            usrServ.saveUser(usr);
            return ResponseEntity.ok(usr);

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(("/{username}"))
    public ResponseEntity<?> eliminar(@PathVariable String usrname){
        try {
            usrServ.deleteUser(usrname);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
}
