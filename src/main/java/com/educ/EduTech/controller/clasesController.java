package com.educ.EduTech.controller;

import com.educ.EduTech.model.cursos;
import com.educ.EduTech.services.cursosServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clases")
public class clasesController {
    @Autowired
    private cursosServices cursoServ;

    @GetMapping
    public ResponseEntity<List<cursos>> listar(){
        List<cursos> curso = cursoServ.findAll();
        if (curso.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(curso);
    }

    @PostMapping
    public ResponseEntity<cursos> guardarCurso(@RequestBody cursos curso){
        curso = new cursos();
        cursoServ.saveCurso(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(curso);
    }

    @GetMapping("/{id}")
    public ResponseEntity<cursos> actualizarCurso(@PathVariable String id, @RequestBody cursos curso){
        try {
            cursos curs = cursoServ.findById(id);
            curs.setId(id);
            curs.setCosto(curso.getCosto());
            curs.setDuracion(curso.getDuracion());
            curs.setInst(curso.getInst());
            curs.setNombre(curso.getNombre());

            cursoServ.saveCurso(curs);
            return ResponseEntity.ok(curs);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCurso(@PathVariable String id){
        try {
            cursoServ.deleteCurso(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
