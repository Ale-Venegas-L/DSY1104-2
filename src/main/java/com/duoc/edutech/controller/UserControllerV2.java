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
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v2/users")
@Tag(
    name = "Usuarios",
    description = "Operaciones relacionadas a los usuarios"
)
public class UserControllerV2 {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserModelAssembler assembler;

    //buscar todo
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<User>> fetchUsers(){
        List<EntityModel<User>> users = userRepo.findAll().stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(users, 
        linkTo(methodOn(UserControllerV2.class).fetchUsers()).withSelfRel());
    }

    //buscar
    @GetMapping(value = "/{username}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<User> getUser(@PathVariable String username){
        User user = userRepo.findByUsername(username);
        return assembler.toModel(user);
    }

    //modificar
    @PutMapping(value = "/{username}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<User>> updateUser(@RequestBody User user, @PathVariable String username){
        user.setUsername(username);
        User updateUser = userRepo.save(user);
        return ResponseEntity.ok(assembler.toModel(updateUser));
    }
    //añadir
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<User>> createUser(@RequestBody User user){
        User newUser = userRepo.save(user);
        return ResponseEntity
        .created(linkTo(methodOn(UserControllerV2.class)
        .getUser(newUser.getUsername())).toUri())
        .body(assembler.toModel(newUser));
    }

    //eliminar
    @DeleteMapping(value = "/{username}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteUser(@PathVariable String username){
        userRepo.deleteById(username);
        return ResponseEntity.noContent().build();
    }
}
