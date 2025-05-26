package com.duoc.edutech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.duoc.edutech.model.Curso;
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

    public Material updateMaterial(Material mtr, Curso crs){
        Material material = materialRepo.findByCurso(crs);

        material.setId(mtr.getId());
        material.setCurso(mtr.getCurso());

        return materialRepo.save(material);
    }

    public void deleteMaterial(Curso crs){
        Material material = materialRepo.findByCurso(crs);
        materialRepo.delete(material);
    }
}
