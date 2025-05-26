package com.duoc.edutech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.duoc.edutech.model.Curso;


@Repository
public interface CursoRepo extends JpaRepository<Curso, String>{
    Curso findByIdCurso(String idCurso);
}
