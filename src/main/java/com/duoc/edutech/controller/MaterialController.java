package com.duoc.edutech.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.duoc.edutech.model.Material;
import com.duoc.edutech.service.MaterialService;

@RestController
public class MaterialController {
    @Autowired
    private MaterialService materialService;

    @PostMapping("/api/materials")
    public Material saveMaterial(@RequestBody Material material){
        return materialService.saveMaterial(material);
    }

    @GetMapping("/api/materials")
    public List<Material> fetchMaterials(){
        return materialService.fetchMaterials();
    }

    @PutMapping("/api/materials/{id}")
    public Material updateMaterial(@RequestBody Material material, @PathVariable String id){
        return materialService.updateMaterial(material, id);
    }

    @DeleteMapping("/api/materials/{id}")
    public String deleteMaterial(@PathVariable String id){
        materialService.deleteMaterial(id);
        return "Material eliminado";
    }
}
