package com.duoc.edutech.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Curso {
    @Id
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
