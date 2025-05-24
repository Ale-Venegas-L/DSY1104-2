package com.duoc.edutech.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.duoc.edutech.model.User;
import com.duoc.edutech.repository.UserRepo;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public List<User> getUsers(){
        return userRepo.mostrarUsers();
    }

    public User saveUser(User usr){
        return userRepo.actualizaUser(usr);
    }

    public User getUsernName(String usr){
        return userRepo.buscaUser(usr);
    }

    public User updateUser(User usr){
        return userRepo.actualizaUser(usr);
    }

    public String deleteUser(String usr){
        userRepo.eliminar(usr);
        return "Usuario eliminado";
    }
}
