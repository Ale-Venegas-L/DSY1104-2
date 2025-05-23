package com.duoc.edutech.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.duoc.edutech.model.Curso;

@Repository
public class CursoRepo {
    private List<Curso> cursos = new ArrayList<>();

    public List<Curso> mostrarCursos(){
        return cursos;
    }

    public Curso buscarCurso(String id){
        for (Curso curso : cursos) {
            if (curso.getId()==id) {
                return curso;
            }
        }
        return null;
    }

    public Curso guardarCurso(Curso crs){
        cursos.add(crs);
        return crs;
    }

    public void eliminarCurso(String id){
        Curso curso = buscarCurso(id);
        if (curso!=null) {
            cursos.remove(curso);
        }
    }

    public Curso actualizarCurso(Curso crs){
        Curso curso1 = new Curso();
        for (Curso curso : cursos) {
            if (crs.getId()==curso.getId()) {
                curso1.setId(crs.getId());
            }
        }

        curso1.setCosto(crs.getCosto());
        curso1.setDuracion(crs.getDuracion());
        curso1.setInstruct(crs.getInstruct());
        curso1.setNombreCurso(crs.getNombreCurso());
        return curso1;
    }

}
