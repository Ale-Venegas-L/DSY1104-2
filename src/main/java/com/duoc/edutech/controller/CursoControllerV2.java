package com.duoc.edutech.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.duoc.edutech.assemblers.CursoModelAssembler;
import com.duoc.edutech.model.Curso;
import com.duoc.edutech.repository.CursoRepo;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v2/cursos")
@Tag(
    name = "Cursos V2",
    description = "Operaciones respecto a los materiales, con HateOAS incluído")
public class CursoControllerV2 {
    @Autowired
    private CursoRepo cursoRepo;

    @Autowired
    private CursoModelAssembler assembler;

    

    @Operation(summary = "Obtener todos los cursos", description = "Busca y obtiene una lista con todos los cursos registrados")
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cursos encontrados"),
        @ApiResponse(responseCode = "404", description = "Cursos no encontrados")
    })
    public CollectionModel<EntityModel<Curso>> fetchCursos(){
        List<EntityModel<Curso>> cursos = cursoRepo.findAll().stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(cursos,
        linkTo(methodOn(CursoControllerV2.class).fetchCursos()).withSelfRel());
    }

    @Operation(summary = "Obtener un curso", description = "Busca por su código y obtiene un curso.")
    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Curso encontrado"),
        @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    public EntityModel<Curso> getCurso(@PathVariable String id){
        Curso curso = cursoRepo.findByIdCurso(id);
        return assembler.toModel(curso);
    }

    @Operation(summary = "Actualiza un curso", description = "Actualiza todos los datos de un curso, buscando por su código")
    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Curso actualizado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Error en formato"),
        @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    public ResponseEntity<EntityModel<Curso>> updateCurso(@RequestBody Curso curso, @PathVariable String id){
        curso.setIdCurso(id);
        Curso updated = cursoRepo.save(curso);
        return ResponseEntity.ok(assembler.toModel(updated));
    }

    @Operation(summary = "Guardar nuevo curso", description = "Crea y registra un nuevo curso")
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Curso guardado"),
        @ApiResponse(responseCode = "400", description = "Error en formato")
    })
    public ResponseEntity<EntityModel<Curso>>createCurso(@RequestBody Curso curso){
        Curso newCurso = cursoRepo.save(curso);
        return ResponseEntity
        .created(linkTo(methodOn(CursoControllerV2.class)
        .getCurso(newCurso.getIdCurso())).toUri())
        .body(assembler.toModel(newCurso));
    }
    @Operation(summary = "Elimina un curso", description = "Elimina un curso, buscando por su nombre")
    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Curso eliminado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Error en formato"),
        @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    public ResponseEntity<?> deleteCurso(@PathVariable String id){
        cursoRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
