package com.duoc.edutech.model;

import java.util.ArrayList;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String emailInst;

    @Column(nullable =  false)
    private String pNombre;

    @Column(nullable =  true)
    private String sNombre;
    
    @Column(nullable =  false)
    private String pApellido;

    @Column(nullable =  false)
    private String mApellido;

    @Column(nullable =  false)
    private String titulo;

    @Column(nullable =  true)
    private int numcel;

    @OneToMany
    private ArrayList<Curso> cursos;
}
