package com.duoc.edutech.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Material {
    @Id
    private String id;

    @ManyToOne
    private Curso curso;
}
