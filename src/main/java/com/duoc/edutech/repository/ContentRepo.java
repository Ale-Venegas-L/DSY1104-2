package com.duoc.edutech.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.duoc.edutech.model.Contenido;

@Repository
public class ContentRepo {
    private List<Contenido> content = new ArrayList<>();

    public List<Contenido> mostrarContenidos(){
        return content;
    }

    public Contenido buscarContenido(String id){
        for (Contenido contenido : content) {
            if (contenido.getId()==id) {
                return contenido;                
            }
        }
        return null;
    }

    public Contenido guardarContenido(Contenido contenido){
        content.add(contenido);
        return contenido;
    }

    public void eliminarContent(String id){
        Contenido contentido = buscarContenido(id);
        if (contentido!=null) {
            content.remove(contentido);
        }
    }

    public Contenido actualizarContenido(Contenido contendo){
        Contenido contenido2 = new Contenido();
        for (Contenido contenido : content) {
            if (contendo.getId()==contenido.getId()) {
                contenido2.setId(contendo.getId());
            }
        }

        contenido2.setCurso(contendo.getCurso());
        contenido2.setInstructor(contendo.getInstructor());
        contenido2.setNombre(contendo.getNombre());
        contenido2.setTipo(contendo.getTipo());
        return contenido2;
    }
}
