package com.duoc.edutech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.duoc.edutech.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, String>{

    User findByUsername(String username);

}
