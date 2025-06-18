package com.duoc.edutech;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.duoc.edutech.model.Instructor;
import com.duoc.edutech.repository.InstructorRepo;
import com.duoc.edutech.service.InstructorService;

import java.util.List;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InstructorServiceTest {

    @Autowired
    private InstructorService instructorService;

    @MockBean
    private InstructorRepo instructorRepo;

    @Test
    public void testFindAll(){
        when(instructorRepo.findAll()).thenReturn(List.of(new Instructor(
            "correo@gmail.com", "José", "José", 
            "José", "José", "null", 9999, 
            null)));
        List<Instructor> instructores = instructorService.fetchInstructors();

        assertNotNull(instructores);
        assertEquals(1, instructores.equals(instructores));
    }

    @Test
    public void testFindByMail(){
        String mail = "correo@gmail.com";
        Instructor instructor = new Instructor(mail, "José", "José", 
            "José", "José", "null", 9999, 
            null);
        when(instructorRepo.findByEmailInst(mail)).thenReturn(instructor);

        Instructor found = instructorService.findInstructor(mail);

        assertNotNull(found);
        assertEquals(mail, found.getEmailInst());
    }

    @Test
    public void testSave(){
        Instructor instructor = new Instructor(
            "correo@gmail.com", "José", "José", 
            "José", "José", "null", 9999, 
            null);
        when(instructorRepo.save(instructor)).thenReturn(instructor);

    }

    @Test
    public void testDeleteByMail(){
        String mail = "correo@gmail.com";
        doNothing().when(instructorRepo).deleteById(mail);
        instructorService.deleteInstructor(mail);
        verify(instructorRepo, times(1)).deleteById(mail);
    }

}
