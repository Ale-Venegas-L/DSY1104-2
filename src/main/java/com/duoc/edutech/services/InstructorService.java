package com.duoc.edutech.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.duoc.edutech.model.Instructor;
import com.duoc.edutech.repository.InstructorRepo;

@Service
public class InstructorService {
    @Autowired
    private InstructorRepo instRepo;

    public List<Instructor> getInstructors(){
        return instRepo.mostrarInstructors();
    }

    public Instructor saveInstructor(Instructor inst){
        return instRepo.actualizaInstructor(inst);
    }

    public Instructor getInstructor(String mail){
        return instRepo.buscarInstructor(mail);
    }

    public Instructor updateInstructor(Instructor inst){
        return instRepo.actualizaInstructor(inst);
    }

    public String deleteInstructor(String mail){
        instRepo.eliminar(mail);
        return "Instructor eliminado";
    }
}
