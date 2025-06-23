package com.duoc.edutech.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.duoc.edutech.assemblers.UserModelAssembler;
import com.duoc.edutech.model.User;
import com.duoc.edutech.repository.UserRepo;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v2/users")
@Tag(
    name = "Usuarios V2",
    description = "Operaciones relacionadas a los usuarios, con HateOAS incluído"
)
public class UserControllerV2 {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserModelAssembler assembler;

    //buscar todo
    @Operation(summary = "Obtener todos los usuarios", description = "Busca y obtiene una lista con todos los usuarios registrados")
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuarios encontrados"),
        @ApiResponse(responseCode = "404", description = "Usuarios no encontrados")
    })
    public CollectionModel<EntityModel<User>> fetchUsers(){
        List<EntityModel<User>> users = userRepo.findAll().stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(users, 
        linkTo(methodOn(UserControllerV2.class).fetchUsers()).withSelfRel());
    }

    //buscar
    @Operation(summary = "Obtener un usuario", description = "Busca por su username y obtiene un usuario.")
    @GetMapping(value = "/{username}", produces = MediaTypes.HAL_JSON_VALUE)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public EntityModel<User> getUser(@PathVariable String username){
        User user = userRepo.findByUsername(username);
        return assembler.toModel(user);
    }

    //modificar
    @Operation(summary = "Actualiza un usuario", description = "Actualiza todos los datos de un usuario, buscando por su nombre")
    @PutMapping(value = "/{username}", produces = MediaTypes.HAL_JSON_VALUE)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Error en formato"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<EntityModel<User>> updateUser(@RequestBody User user, @PathVariable String username){
        user.setUsername(username);
        User updateUser = userRepo.save(user);
        return ResponseEntity.ok(assembler.toModel(updateUser));
    }
    //añadir
    @Operation(summary = "Guardar nuevo usuario", description = "Crea y registra un nuevo usuario")
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario guardado"),
        @ApiResponse(responseCode = "400", description = "Error en formato")
    })
    public ResponseEntity<EntityModel<User>> createUser(@RequestBody User user){
        User newUser = userRepo.save(user);
        return ResponseEntity
        .created(linkTo(methodOn(UserControllerV2.class)
        .getUser(newUser.getUsername())).toUri())
        .body(assembler.toModel(newUser));
    }

    //eliminar
    @Operation(summary = "Elimina un usuario", description = "Elimina un usuario, buscando por su nombre")
    @DeleteMapping(value = "/{username}", produces = MediaTypes.HAL_JSON_VALUE)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario eliminado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Error en formato"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<?> deleteUser(@PathVariable String username){
        userRepo.deleteById(username);
        return ResponseEntity.noContent().build();
    }
}
