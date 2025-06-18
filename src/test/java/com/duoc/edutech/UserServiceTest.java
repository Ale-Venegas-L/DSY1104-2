package com.duoc.edutech;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.duoc.edutech.model.User;
import com.duoc.edutech.repository.UserRepo;
import com.duoc.edutech.service.UserService;

import java.util.List;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepo userRepo;

    @Test
    public void testFindAll(){
        when(userRepo.findAll()).thenReturn(List.of(new User("chucrut", "chucrut", "chucrut@gmail.com", 0000)));
        List<User> users = userService.fetchUsers();

        assertNotNull(users);
        assertEquals(1, users.size());
    }

    @Test
    public void testFindByUser() {
        String usr = "chucrut";
        User user = new User(usr, "chucrut", "chucrut@gmail.com", 0000);

        when(userRepo.findByUsername(usr)).thenReturn(user);

        User found = userService.findUser(usr);

        assertNotNull(found);
        assertEquals(usr, found.getUsername());
    }

    @Test
    public void testSave(){
        User user = new User("chucrut", "chucrut", "chucrut@gmail.com", 0000);

        when(userRepo.save(user)).thenReturn(user);

        User saved = userService.saveUser(user);

        assertNotNull(saved);
        assertEquals("chucrut", saved.getUsername());
    }

    @Test
    public void testDeleteByUser(){
        String usrname = "chucrut";

        doNothing().when(userRepo).deleteById(usrname);

        userService.deleteUser(usrname);

        verify(userRepo, times(1)).deleteById(usrname);
    }

}
