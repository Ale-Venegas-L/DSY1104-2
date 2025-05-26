package com.duoc.edutech.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.duoc.edutech.model.Instructor;
import com.duoc.edutech.repository.InstructorRepo;

@Service
public class InstructorService {
    @Autowired
    private InstructorRepo instructorRepo;

    public Instructor saveInstructor(Instructor inst){
        return instructorRepo.save(inst);
    }

    public List<Instructor> fetchInstructors(){
        return instructorRepo.findAll();
    }

    public Instructor updateInstructor(Instructor inst, String email){
        Instructor instructor = instructorRepo.findByEmailInst(email);

        instructor.setPNombre(inst.getPNombre());
        instructor.setSNombre(inst.getSNombre());
        instructor.setPApellido(inst.getPApellido());
        instructor.setMApellido(inst.getMApellido());
        instructor.setTitulo(inst.getTitulo());
        instructor.setCursos(inst.getCursos());

        return instructorRepo.save(instructor);
    }

    public void deleteInstructor(String email){
        Instructor instructor = instructorRepo.findByEmailInst(email);
        instructorRepo.delete(instructor);
    }
}
