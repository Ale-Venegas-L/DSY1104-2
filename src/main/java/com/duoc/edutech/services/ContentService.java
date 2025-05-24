package com.duoc.edutech.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.duoc.edutech.model.Contenido;
import com.duoc.edutech.repository.ContentRepo;

@Service
public class ContentService {
    @Autowired
    private ContentRepo contentRepo;

    public List<Contenido> getContenidos(){
        return contentRepo.mostrarContenidos();
    }

    public Contenido saveContenido(Contenido contenido){
        return contentRepo.guardarContenido(contenido);
    }

    public Contenido getContenido(String id){
        return contentRepo.buscarContenido(id);
    }

    public Contenido updateContenido(Contenido contenido){
        return contentRepo.actualizarContenido(contenido);
    }

    public String deleteContenido(String id){
        contentRepo.eliminarContent(id);
        return "Contenido eliminado";
    }
}
