package com.duoc.edutech.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.duoc.edutech.model.Curso;
import com.duoc.edutech.repository.CursoRepo;

@Service
public class CursoService {
    @Autowired
    private CursoRepo cursoRepo;

    public Curso saveCurso(Curso crs){
        return cursoRepo.save(crs);
    }

    public List<Curso> fetchCursos(){
        return cursoRepo.findAll();
    }

    public Curso updateCurso(Curso crs, String id){
        Curso curso = cursoRepo.findByIdCurso(id);

        curso.setNombreCurso(crs.getNombreCurso());
        curso.setCosto(crs.getCosto());
        curso.setDuracion(crs.getDuracion());
        curso.setInstructor(crs.getInstructor());

        return cursoRepo.save(curso);
    }

    public void deleteCurso(String id){
        Curso curso = cursoRepo.findByIdCurso(id);
        cursoRepo.delete(curso);
    }
}
