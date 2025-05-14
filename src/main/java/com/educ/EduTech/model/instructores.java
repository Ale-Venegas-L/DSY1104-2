package com.educ.EduTech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "instructores")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class instructores {
    @Id
    @OneToMany(mappedBy = "cursos")
    private int rut_inst;

    @Column(nullable = false)
    private char dv_inst;

    @Column(nullable = false)
    private String pnombre_inst;

    @Column(nullable = true)
    private String snombre_inst;

    @Column(nullable = false)
    private String apellidop_inst;

    @Column(nullable = false)
    private String appellidom_inst;

    @Column(nullable = false)
    private int nro_tel_inst;

    @Column(nullable = false)
    private String email_inst;

    @Column(nullable = false)
    private String titulo;
}

