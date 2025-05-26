package com.duoc.edutech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.duoc.edutech.model.Material;
import com.duoc.edutech.repository.MaterialRepo;

@Service
public class MaterialService {
    @Autowired
    private MaterialRepo materialRepo;

    public Material saveMaterial(Material mtr){
        return materialRepo.save(mtr);
    }

    public List<Material> fetchMaterials(){
        return materialRepo.findAll();
    }

    public Material getMaterialById(String id){
    return materialRepo.findById(id).orElseThrow(() -> new RuntimeException("Material no encontrado"));
    }

    public Material updateMaterial(Material mtr, String id){
        Material material = getMaterialById(id);
        material.setCurso(mtr.getCurso());
        return materialRepo.save(material);
    }

    public void deleteMaterial(String id){
        Material material = getMaterialById(id);
        materialRepo.delete(material);
    }
    
}
