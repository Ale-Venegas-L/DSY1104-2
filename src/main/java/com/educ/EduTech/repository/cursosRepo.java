package com.educ.EduTech.repository;

import com.educ.EduTech.model.cursos;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository; 

public interface cursosRepo extends JpaRepository{
    List<cursos> findById(String id);
    String deleteById(String id);
}
