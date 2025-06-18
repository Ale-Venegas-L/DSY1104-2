package com.duoc.edutech;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.duoc.edutech.model.Material;
import com.duoc.edutech.repository.MaterialRepo;
import com.duoc.edutech.service.MaterialService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class MaterialServiceTest {

    @Autowired
    private MaterialService materialService;

    @MockBean
    private MaterialRepo materialRepo;

    @Test
    public void testFindAll(){
        when(materialRepo.findAll()).thenReturn(List.of(new Material("000", null)));
        List<Material> materiales = materialService.fetchMaterials();
        assertNotNull(materiales);
        assertEquals(1, materiales.size());
    }

    @Test
    public void testFindById(){
        String id = "000";
        Material material = new Material(id, null);
        when(materialRepo.findById(id)).thenReturn(Optional.of(material));
        Material found = materialService.getMaterialById(id);
        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    @Test
    public void testSaveMaterial(){
        Material material = new Material("000", null);
        when(materialRepo.save(material)).thenReturn(material);
        Material saved = materialService.saveMaterial(material);
        assertNotNull(saved);
        assertEquals("000", saved.getId());
    }

    @Test
    public void testDeleteMaterial(){
        String id = "000";
        doNothing().when(materialRepo).deleteById(id);
        materialService.deleteMaterial(id);
        verify(materialRepo, times(1)).deleteById(id);
    }

}
