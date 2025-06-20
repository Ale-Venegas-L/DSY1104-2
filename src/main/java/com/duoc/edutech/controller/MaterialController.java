package com.duoc.edutech.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.duoc.edutech.model.Material;
import com.duoc.edutech.service.MaterialService;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/materials")
@Tag(name = "Materiales",
description = "Operaciones respecto a los materiales")
public class MaterialController {
    @Autowired
    private MaterialService materialService;

    @Operation(
        summary = "Guardar nuevo material",
        description = "Crea y registra un nuevo material"
    )
    @PostMapping
    @ApiResponses( value = {
        @ApiResponse(responseCode = "200", description = "Material creado correctamente"),
        @ApiResponse(responseCode = "400", description = "Error en el formato")
    })
    public Material saveMaterial(@RequestBody Material material){
        return materialService.saveMaterial(material);
    }

    @Operation(
        summary = "Obtener todos los materiales",
        description = "Busca y obtiene una lista con todos los materiales")
    @GetMapping
    @ApiResponses( value = {
        @ApiResponse(responseCode = "200", description = "Materiales encontrados"),
        @ApiResponse(responseCode = "404", description = "Materiales no encontrados")
    })
    public List<Material> fetchMaterials(){
        return materialService.fetchMaterials();
    }

    @Operation(summary = "Actualizar un material",
    description = "Actualiza todos los datos de un material, buscando por su ID.")
    @PutMapping("/{id}")
    @ApiResponses( value = {
        @ApiResponse(responseCode = "200", description = "Material actualizado"),
        @ApiResponse(responseCode = "400", description = "Error en formato"),
        @ApiResponse(responseCode = "404", description = "Material no encontrado")
    })
    public Material updateMaterial(@RequestBody Material material, @PathVariable String id){
        return materialService.updateMaterial(material, id);
    }

    @Operation(summary = "Elimina un material",
    description = "Elimina un material, buscando por su ID")
    @DeleteMapping("/{id}")
    @ApiResponses( value = {
        @ApiResponse(responseCode = "200", description = "Material eliminado"),
        @ApiResponse(responseCode = "400", description = "Error en formato"),
        @ApiResponse(responseCode = "404", description = "Material no encontrado")
    })
    public String deleteMaterial(@PathVariable String id){
        materialService.deleteMaterial(id);
        return "Material eliminado";
    }
}
