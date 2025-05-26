package com.duoc.edutech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.duoc.edutech.model.Material;
import com.duoc.edutech.model.Curso;

@Repository
public interface MaterialRepo extends JpaRepository<Material, String>{
    Material findByCurso(Curso curso);
}
