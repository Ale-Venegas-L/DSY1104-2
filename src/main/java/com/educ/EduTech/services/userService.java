package com.educ.EduTech.services;

import com.educ.EduTech.model.user;
import com.educ.EduTech.repository.userRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional

public class userService {
    @Autowired
    private static userRepo userRepo;

    public List<user> findAll(){
        return userRepo.findAll();
    }

    public user findByUser(String usrnm){
        return (user) userRepo.findByUsername(usrnm);
    }
    
    public user saveUser(user usr){
        return userRepo.save(usr);
    }

    public user updateUser(user usr){
        return userRepo.save(usr);
    };

    public void deleteUser(String usrnm){
        userRepo.deleteByUsername(usrnm);
    }

}
