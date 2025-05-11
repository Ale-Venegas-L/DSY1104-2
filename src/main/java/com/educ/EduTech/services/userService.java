package com.educ.EduTech.services;

import com.educ.EduTech.model.user;
import com.educ.EduTech.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional

public class userService {
    @Autowired
    private UserRepo userRepo;

    public List<user> findAll(){
        return userRepo.findAll();
    }

    public user findByUser(String usrnm){
        return userRepo.findByUsername(usrnm).get(0);
    }
    
    public user save(user usr){
        return userRepo.save(usr);
    }

    public void delete(String usrnm){
        userRepo.deleteByUsername(usrnm);
    }
}
