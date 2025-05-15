package com.educ.EduTech.repository;

import com.educ.EduTech.model.user;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface userRepo extends JpaRepository<user, String>{ 
    List<user> findByUsername(String username);

    String deleteByUsername(String username);

}
