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

    public static List<cursos> findAll() {
        return cursosRepo.findAll();
    }

    public static cursos findById(String id){
        return (cursos) cursosRepo.findById(id);
    }

    public static void saveCurso(){}

    public static void updateCurso(){}

    public static void deleteCurso(){}
}
