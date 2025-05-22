package com.duoc.edutech.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.duoc.edutech.model.User;

@Repository
public class UserRepo {
    private List<User> users = new ArrayList<>();

    //mostrar todo
    public List<User> mostrarUsers(){
        return users;
    }
    //buscar por user
    public User buscaUser(String usrnm){
        for (User user : users) {
            if (user.getUsername()==usrnm) {
                return user;
            }
        }
        return null;
    }
    //añadir
    public User guardaUser(User usr){
        users.add(usr);
        return usr;
    }

    //eliminar
    public void eliminar(String usr){
        User user = buscaUser(usr);
        if (user!=null) {
            users.remove(user);
        }
    }
    //modificar
    public User actualizaUser(User usr){
        User usr1 = new User();
        for (User user : users) {
            if (user.getUsername()==usr.getUsername()) {
                usr1.setUsername(usr.getUsername());
            }
        }
        usr1.setEmail(usr.getEmail());
        usr1.setPassword(usr.getPassword());
        usr1.setNumcel(usr.getNumcel());
        return usr1;
    }
}
