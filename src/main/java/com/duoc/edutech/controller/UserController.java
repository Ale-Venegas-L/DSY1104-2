package com.duoc.edutech.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.duoc.edutech.model.User;
import com.duoc.edutech.service.UserService;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Usuarios", description = "Operaciones respecto a los usuarios")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Guardar nuevo usuario", description = "Crea y registra un nuevo usuario")
    @PostMapping
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario guardado"),
        @ApiResponse(responseCode = "400", description = "Error en formato")
    })
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @Operation(summary = "Obtener todos los usuarios", description = "Busca y obtiene una lista con todos los usuarios registrados")
    @GetMapping
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuarios encontrados"),
        @ApiResponse(responseCode = "404", description = "Usuarios no encontrados")
    })
    public List<User> fetchUsers(){
        return userService.fetchUsers();
    }

    @Operation(summary = "Actualiza un usuario", description = "Actualiza todos los datos de un usuario, buscando por su nombre")
    @PutMapping("/{username}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Error en formato"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public User updateUser(@RequestBody User user, @PathVariable String username){
        return userService.updateUser(user, username);
    }

    @Operation(summary = "Elimina un usuario", description = "Elimina un usuario, buscando por su nombre")
    @DeleteMapping("/{username}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario eliminado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Error en formato"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public String deleteUser(@PathVariable String username){
        userService.deleteUser(username);
        return "Usuario eliminado";
    }

}
