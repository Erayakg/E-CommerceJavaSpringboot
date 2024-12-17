package com.example.demo.entites;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "yoneticiler")
public class Yoneticiler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "kullanici_id")
    private Kullanici kullanici;

    private String departman;

}
