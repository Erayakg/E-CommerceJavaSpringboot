package com.example.demo.entites;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "iade")
public class Iade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "siparis_id")
    private Siparis siparis;

    private String durum;
    private String tarih;

}
