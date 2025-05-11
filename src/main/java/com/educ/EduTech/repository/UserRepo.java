package com.educ.EduTech.repository;

import com.educ.EduTech.model.user;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface UserRepo extends JpaRepository<user, String>{ 
    List<user> findByUsername(String username);

    @Query("SELECT u from user u where u.email= :email")
    List<user> findByEmail(String email);
    
    void deleteByUsername(String username);

}
