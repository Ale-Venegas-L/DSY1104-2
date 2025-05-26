package com.duoc.edutech.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idCurso;

    @Column(nullable =  false)
    private String nombreCurso;

    @Column(nullable =  false)
    private int costo;
    
    @Column(nullable =  false)
    private float duracion;

    @ManyToOne
    private Instructor instructor;
}
