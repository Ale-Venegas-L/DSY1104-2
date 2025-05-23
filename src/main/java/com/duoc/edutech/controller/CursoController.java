package com.duoc.edutech.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.duoc.edutech.model.Curso;
import com.duoc.edutech.services.CursoService;

@RestController
@RequestMapping("/api/v1/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public List<Curso> listaCursos(){
        return cursoService.getCursos();
    }

    @PostMapping
    public Curso agregarCurso(@RequestBody Curso crs){
        return cursoService.saveCurso(crs);
    }

    @GetMapping("{id}")
    public Curso buscarCurso(@PathVariable String id){
        return cursoService.getCurso(id);
    }

    @DeleteMapping("{id}")
    public String eliminarCurso(@PathVariable String id){
        return cursoService.deleteCurso(id);
    }

    @PutMapping
    public Curso actualizarCurso(@PathVariable String id, @RequestBody Curso crs){
        return cursoService.updateCurso(crs);
    }
}
