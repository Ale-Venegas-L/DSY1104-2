package com.educ.EduTech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cursos")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class cursos {
    @Id
    private String id;

    @Column
    private String nombre;

    @Column
    private int duracion, costo;

    @ManyToOne
    @JoinColumn(name = "rut_inst")
    private instructores inst;
}
