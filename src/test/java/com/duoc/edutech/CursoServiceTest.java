package com.duoc.edutech;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.duoc.edutech.model.Curso;
import com.duoc.edutech.repository.*;
import com.duoc.edutech.service.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CursoServiceTest {
    @Autowired
    private CursoService cursoService;

    @MockBean
    private CursoRepo cursoRepo;

    @Test
    public void testFindAll(){
        when(cursoRepo.findAll()).thenReturn(List.of(new Curso("DSY101", "Desarrollo de Bloques", 1000, 15, null)));
        
        List<Curso> cursos = cursoService.fetchCursos();

        assertNotNull(cursos);
        assertEquals(1, cursos.size());
    }

    @Test
    public void testFindById(){
        String id = "DSY101";
        Curso curso = new Curso(id, "Desarrollo de Bloques", 1000, 15, null);

        when(cursoRepo.findByIdCurso(id)).thenReturn(curso);

        Curso found = cursoService.findCurso(id);
        
        assertNotNull(found);
        assertEquals(id, found.getIdCurso());
    }

    @Test
    public void testSave(){
        Curso curso = new Curso("DSY101", "Desarrollo de Bloques", 1000, 15, null);
        
        when(cursoRepo.save(curso)).thenReturn(curso);

        Curso saved = cursoService.saveCurso(curso);

        assertNotNull(saved);
        assertEquals("DSY101", saved.getIdCurso());
    }
    
    @Test
    public void testDelete(){
        String id = "DSY101";

        doNothing().when(cursoRepo).deleteById(id);

        cursoService.deleteCurso(id);

        verify(cursoRepo, times(1)).deleteById(id);
    }
}
