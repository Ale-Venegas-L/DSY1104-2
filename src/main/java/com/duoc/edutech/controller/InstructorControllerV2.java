package com.duoc.edutech.controller;

import java.util.stream.Collectors;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.duoc.edutech.assemblers.InstructorModelAssembler;
import com.duoc.edutech.model.Instructor;
import com.duoc.edutech.repository.InstructorRepo;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v2/instructors")
@Tag(
    name = "Instructores V2",
    description = "Operaciones relacionadas a los instructores, con HateOAS incluído"
)
public class InstructorControllerV2 {

    @Autowired
    private InstructorRepo instructorRepo;

    @Autowired
    private InstructorModelAssembler assembler;

    @Operation(summary = "Obtener todos los instructores", description = "Busca y obtiene una lista con todos los instructores registrados")
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuarios encontrados"),
        @ApiResponse(responseCode = "404", description = "Usuarios no encontrados")
    })
    public CollectionModel<EntityModel<Instructor>> fetchInstructors(){
        List<EntityModel<Instructor>> instructors = instructorRepo.findAll().stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(instructors,
        linkTo(methodOn(InstructorControllerV2.class).fetchInstructors()).withSelfRel());
    }

    @Operation(summary = "Obtener un instructor", description = "Busca por su correo y obtiene un instructor.")
    @GetMapping(value = "/{email}", produces = MediaTypes.HAL_JSON_VALUE)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Instructor encontrado"),
        @ApiResponse(responseCode = "404", description = "Instructor no encontrado")
    })
    public EntityModel<Instructor> getInstructor (@PathVariable String email){
        Instructor instructor = instructorRepo.findByEmailInst(email);
        return assembler.toModel(instructor);
    }

    @Operation(summary = "Actualiza un instructor", description = "Actualiza todos los datos de un instructor, buscando por su nombre")
    @PutMapping(value = "/{email}", produces = MediaTypes.HAL_JSON_VALUE)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Instructor actualizado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Error en formato"),
        @ApiResponse(responseCode = "404", description = "Instructor no encontrado")
    })
    public ResponseEntity<EntityModel<Instructor>> updateInstructor(@PathVariable String email, @RequestBody Instructor instructor){
        instructor.setEmailInst(email);
        Instructor updatedInstructor = instructorRepo.save(instructor);
        return ResponseEntity.ok(assembler.toModel(updatedInstructor));
    }

    @Operation(summary = "Guardar nuevo instructor", description = "Crea y registra un nuevo instructor")
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Instructor guardado"),
        @ApiResponse(responseCode = "400", description = "Error en formato")
    })
    public ResponseEntity<EntityModel<Instructor>> createInstructor(@RequestBody Instructor instructor){
        Instructor newInstructor = instructorRepo.save(instructor);
        return ResponseEntity.created(linkTo(methodOn(InstructorControllerV2.class)
        .getInstructor(newInstructor.getEmailInst())).toUri())
        .body(assembler.toModel(newInstructor));
    }

    @Operation(summary = "Elimina un usuario", description = "Elimina un usuario, buscando por su nombre")
    @DeleteMapping(value = "/{email}", produces = MediaTypes.HAL_JSON_VALUE)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario eliminado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Error en formato"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<?> deleteInstructor(@PathVariable String email){
        instructorRepo.deleteById(email);
        return ResponseEntity.noContent().build();
    }
}
