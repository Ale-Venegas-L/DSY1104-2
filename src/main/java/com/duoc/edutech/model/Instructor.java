package com.duoc.edutech.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Instructor {
    private String nombreInst, email, titulo;
    private int numcelInst;
}
