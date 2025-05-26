package com.duoc.edutech.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.duoc.edutech.model.Instructor;
import com.duoc.edutech.service.InstructorService;

@RestController
public class InstructorController {
    @Autowired
    private InstructorService instructorService;
    
    @PostMapping("/instructor")
    public Instructor saveInstructor(@RequestBody Instructor instructor){
        return instructorService.saveInstructor(instructor);
    }

    @GetMapping("/instructor")
    public List<Instructor> fetchInstructors(){
        return instructorService.fetchInstructors();
    }

    @PutMapping("/instructor/email")
    public Instructor updateInstructor(@RequestBody Instructor instructor, @PathVariable String email){
        return instructorService.updateInstructor(instructor, email);
    }


    @DeleteMapping("/instructor/email")
    public String deleteInstructor(@PathVariable String email){
        instructorService.deleteInstructor(email);
        return "Instructor eliminado";
    }
}
