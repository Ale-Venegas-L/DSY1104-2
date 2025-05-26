package com.duoc.edutech.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String username;

    @Column(nullable =  false)
    private String password;
    
    @Column(nullable =  false)
    private String email;
    
    @Column
    private int numcel;
}
