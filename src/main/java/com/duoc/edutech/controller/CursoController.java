package com.duoc.edutech.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.duoc.edutech.model.Curso;
import com.duoc.edutech.service.CursoService;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/cursos")
@Tag(name = "Curso",
description = "Operaciones respecto a los materiales")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @Operation(
        summary = "Guardar nuevo curso",
        description = "Crea y registra un nuevo curso"
    )
    @PostMapping
    @ApiResponses( value = {
        @ApiResponse(responseCode = "200", description = "Curso creado correctamente"),
        @ApiResponse(responseCode = "400", description = "Error en el formato")
    })
    public Curso saveCurso(@RequestBody Curso curso){
        return cursoService.saveCurso(curso);
    }

    @Operation(
        summary = "Obtener todos los cursos",
        description = "Busca y obtiene una lista con todos los materiales")
    @GetMapping
    @ApiResponses( value = {
        @ApiResponse(responseCode = "200", description = "Cursos encontrados"),
        @ApiResponse(responseCode = "404", description = "Cursos no encontrados")
    })
    public List<Curso> fetchCursos(){
        return cursoService.fetchCursos();
    }

    @Operation(summary = "Actualizar un curso",
    description = "Actualiza todos los datos de un curso, buscando por su ID")
    @PutMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Curso actualizado correctamente"),
        @ApiResponse(responseCode = "400", description = "Error en el formato"),
        @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    public Curso updateCurso(@RequestBody Curso curso, @PathVariable String id){
        return cursoService.updateCurso(curso, id);
    }

    @Operation(summary = "Eliminar un curso",
    description = "Elimina un curso, buscando por su ID")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Curso eliminado"),
        @ApiResponse(responseCode = "400", description = "Error en el formato"),
        @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    public String deleteCurso(@PathVariable String id){
        cursoService.deleteCurso(id);
        return "Curso eliminado";
    }
}
