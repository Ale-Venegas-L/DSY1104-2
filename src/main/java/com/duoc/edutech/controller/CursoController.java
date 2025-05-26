package com.duoc.edutech.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.duoc.edutech.model.Curso;
import com.duoc.edutech.service.CursoService;

@RestController
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @PostMapping("/api/cursos")
    public Curso saveCurso(@RequestBody Curso curso){
        return cursoService.saveCurso(curso);
    }

    @GetMapping("/api/cursos")
    public List<Curso> fetchCursos(){
        return cursoService.fetchCursos();
    }

    @PutMapping("/api/cursos/{id}")
    public Curso updateCurso(@RequestBody Curso curso, @PathVariable String id){
        return cursoService.updateCurso(curso, id);
    }

    @DeleteMapping("/api/cursos/{id}")
    public String deleteCurso(@PathVariable String id){
        cursoService.deleteCurso(id);
        return "Curso eliminado";
    }
}
