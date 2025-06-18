package com.duoc.edutech.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String username;

    @Column(nullable =  false)
    private String password;
    
    @Column(nullable =  false)
    private String email;
    
    @Column
    private int numcel;
}
