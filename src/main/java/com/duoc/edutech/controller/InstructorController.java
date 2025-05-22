package com.duoc.edutech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import com.duoc.edutech.model.Instructor;
import com.duoc.edutech.services.InstructorService;

@RestController
@RequestMapping("/api/v1/instructor")
public class InstructorController {
    
    @Autowired
    private InstructorService instructorService;

    @GetMapping
    public List<Instructor> listaInstructors(){
        return instructorService.getInstructors();
    }

    @PostMapping
    public Instructor agregarInstructor(@RequestBody Instructor inst){
        return instructorService.saveInstructor(inst);
    }

    @GetMapping("{email}")
    public Instructor buscarInstructor(@PathVariable String email){
        return instructorService.getInstructor(email);
    }

    @DeleteExchange("{email}")
    public String eliminarInstructor(@PathVariable String email){
        return instructorService.deleteInstructor(email);
    }

    @PutMapping
    public Instructor actualizarInstructor(@PathVariable String email, @RequestBody Instructor inst){
        return instructorService.updateInstructor(inst);
    }
}
