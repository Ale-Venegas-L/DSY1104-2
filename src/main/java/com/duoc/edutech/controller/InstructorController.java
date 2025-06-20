package com.duoc.edutech.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.duoc.edutech.model.Instructor;
import com.duoc.edutech.service.InstructorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/instructors/")
@Tag(name = "Instructores",
description = "Operaciones respecto a los instructores")
public class InstructorController {
    @Autowired
    private InstructorService instructorService;
    
    @Operation(
        summary = "Guardar nuevo instructor",
        description = "Crea y registra un nuevo instructor"
    )
    @PostMapping 
    @ApiResponses( value = {
        @ApiResponse(responseCode = "200", description = "Instructor creado correctamente"),
        @ApiResponse(responseCode = "400", description = "Error en el formato")
    })
    public Instructor saveInstructor(@RequestBody Instructor instructor){
        return instructorService.saveInstructor(instructor);
    }

    @Operation(
        summary = "Obtener todos los instructores",
        description = "Busca y obtiene una lista con todos los instructores")
    @GetMapping
    @ApiResponses( value = {
        @ApiResponse(responseCode = "200", description = "Instructores encontrados"),
        @ApiResponse(responseCode = "404", description = "Instructores no encontrados")
    })
        public List<Instructor> fetchInstructors(){
        return instructorService.fetchInstructors();
    }

    @Operation(summary = "Actualizar un instructor",
    description = "Actualiza todos los datos de un instructor, buscando por su correo.")
    @PutMapping("/{email}")
    @ApiResponses( value = {
        @ApiResponse(responseCode = "200", description = "Instructor actualizado"),
        @ApiResponse(responseCode = "400", description = "Error en formato"),
        @ApiResponse(responseCode = "404", description = "Instructor no encontrado")
    })
    public Instructor updateInstructor(@RequestBody Instructor instructor, @PathVariable String email){
        return instructorService.updateInstructor(instructor, email);
    }

    @Operation(summary = "Elimina un instructor",
    description = "Elimina un instructor, buscando por su correo")
    @DeleteMapping("/{email}")
    @ApiResponses( value = {
        @ApiResponse(responseCode = "200", description = "Instructor eliminado"),
        @ApiResponse(responseCode = "400", description = "Error en formato"),
        @ApiResponse(responseCode = "404", description = "Instructor no encontrado")
    })
    public String deleteInstructor(@PathVariable String email){
        instructorService.deleteInstructor(email);
        return "Instructor eliminado";
    }
    
}
