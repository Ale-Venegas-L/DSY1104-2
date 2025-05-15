package com.educ.EduTech.services;

import com.educ.EduTech.model.cursos;
import com.educ.EduTech.repository.cursosRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional

public class cursosServices {
    @Autowired
    private static cursosRepo cursosRepo;

    public List<cursos> findAll() {
        return cursosRepo.findAll();
    }

    public cursos findById(String id){
        return (cursos) cursosRepo.findById(id);
    }

    public cursos saveCurso(cursos curso){
        return (cursos) cursosRepo.save(curso);
    }

    public cursos updateCurso(cursos curso){
        return (cursos) cursosRepo.save(curso);
    }

    public void deleteCurso(String id){
        cursosRepo.deleteById(id);
    }
}
