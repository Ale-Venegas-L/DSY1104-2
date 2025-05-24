package com.duoc.edutech.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contenido {
    private String id, nombre, tipo;
    private Curso curso;
    private Instructor instructor;

}
