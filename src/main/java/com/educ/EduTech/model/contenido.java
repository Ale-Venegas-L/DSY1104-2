package com.educ.EduTech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contenido")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class contenido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id_curso;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String tipo_material;
}
