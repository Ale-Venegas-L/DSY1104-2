package com.duoc.edutech.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.duoc.edutech.model.Curso;
import com.duoc.edutech.model.Material;
import com.duoc.edutech.service.MaterialService;

@RestController
public class MaterialController {
    @Autowired
    private MaterialService materialService;

    @PostMapping("/material")
    public Material saveMaterial(@RequestBody Material material){
        return materialService.saveMaterial(material);
    }

    @GetMapping("/material")
    public List<Material> fetchMaterials(){
        return materialService.fetchMaterials();
    }

    @PutMapping("/material/{curso}")
    public Material updateMaterial(@RequestBody Material material, @PathVariable Curso curso){
        return materialService.updateMaterial(material, curso);
    }

    @DeleteMapping("/material/{curso}")
    public String deleteMaterial(@PathVariable Curso curso){
        materialService.deleteMaterial(curso);
        return "Material eliminado";
    }
}
