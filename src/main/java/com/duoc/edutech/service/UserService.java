package com.duoc.edutech.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.duoc.edutech.model.User;
import com.duoc.edutech.repository.UserRepo;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public User findUser(String usr){
        User user = userRepo.findByUsername(usr);
        return user;
    }

    public User saveUser(User usr){
        return userRepo.save(usr);
    }

    public List<User> fetchUsers(){
        return userRepo.findAll();
    }

    
    public User updateUser(User usr, String username){
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("Usuario no encontrado");
        }
        user.setEmail(usr.getEmail());
        user.setPassword(usr.getPassword());
        user.setNumcel(usr.getNumcel());
        return userRepo.save(user);
}

    public void deleteUser(String username){
        User usr = userRepo.findByUsername(username);
        if (usr == null) {
            throw new RuntimeException("Usuario no encontrado");
        }
        userRepo.delete(usr); 
}
}
