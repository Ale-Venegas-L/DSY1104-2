package com.duoc.edutech.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Instructor {
    @Id
    private String emailInst;

    @Column
    private String pNombre;

    @Column
    private String sNombre;
    
    @Column
    private String pApellido;

    @Column
    private String mApellido;

    @Column
    private String titulo;

    @Column
    private int numcel;

    @OneToMany(mappedBy = "instructor")
    private List<Curso> cursos = new ArrayList<>();
}
