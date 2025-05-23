package com.duoc.edutech.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.duoc.edutech.model.Curso;
import com.duoc.edutech.repository.CursoRepo;

@Service
public class CursoService {
    @Autowired
    private CursoRepo cursoRepo;

    public List<Curso> getCursos(){
        return cursoRepo.mostrarCursos();
    }

    public Curso saveCurso(Curso crs){
        return cursoRepo.guardarCurso(crs);
    }

    public Curso getCurso(String id){
        return cursoRepo.buscarCurso(id);
    }

    public Curso updateCurso(Curso crs){
        return cursoRepo.actualizarCurso(crs);
    }

    public String deleteCurso(String id){
        cursoRepo.eliminarCurso(id);
        return "Curso eliminado";
    }
}
