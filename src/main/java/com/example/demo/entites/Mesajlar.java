package com.example.demo.entites;

import jakarta.persistence.*;

@Entity
@Table(name = "mesajlar")
public class Mesajlar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "kullanici_id")
    private Kullanici kullanici;

    private String mesaj;

    private String tarih;

    // Getters and Setters
}
