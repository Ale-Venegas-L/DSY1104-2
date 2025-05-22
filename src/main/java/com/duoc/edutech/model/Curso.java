package com.duoc.edutech.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Curso {
    private String nombreCurso, id;
    private double costo, duracion;
    private Instructor instruct;
}
