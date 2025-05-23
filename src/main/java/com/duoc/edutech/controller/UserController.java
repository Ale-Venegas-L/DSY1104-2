package com.duoc.edutech.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.duoc.edutech.model.User;
import com.duoc.edutech.services.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    //listar
    @GetMapping
    public List<User> listaUsers(){
        return userService.getUsers();
    }

    //agregar
    @PostMapping
    public User agregarUser(@RequestBody User usr){
        return userService.saveUser(usr);
    }

    //buscar
    @GetMapping("{username}")
    public User buscarUser(@PathVariable String usr){
        return userService.getUsernName(usr);
    }

    //eliminar
    @DeleteMapping("{username}")
    public String eliminarUser(@PathVariable String usr){
        return userService.deleteUser(usr);
    }
    
    //actualizar
    @PutMapping
    public User actualizarUser(@PathVariable String usrnm, @RequestBody User usr){
        return userService.updateUser(usr);
    }
}

