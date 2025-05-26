package com.duoc.edutech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.duoc.edutech.model.Material;

@Repository
public interface MaterialRepo extends JpaRepository<Material, String>{
    
}
