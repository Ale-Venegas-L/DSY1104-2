package com.duoc.edutech.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.duoc.edutech.model.Contenido;
import com.duoc.edutech.services.ContentService;

@RestController
@RequestMapping("/api/v1/controller")
public class ContentController {
    @Autowired
    private ContentService contentService;

    @GetMapping
    public List<Contenido> listaContenidos(){
        return contentService.getContenidos();
    }

    @PostMapping
    public Contenido agregarContenido(@RequestBody Contenido contenido){
        return contentService.saveContenido(contenido);
    }

    @GetMapping({"id"})
    public Contenido buscarContenido(@PathVariable String id){
        return contentService.getContenido(id);
    }

    @DeleteMapping({"id"})
    public String eliminarContenido(@PathVariable String id){
        return contentService.deleteContenido(id);
    }

    @PutMapping
    public Contenido eliminarContenido(@PathVariable String id, @RequestBody Contenido contenido){
        return contentService.updateContenido(contenido);
    }
}
