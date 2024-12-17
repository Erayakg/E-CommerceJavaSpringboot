package com.example.demo.entites;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "kargo")
public class Kargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "siparis_id")
    private Siparis siparis;

    private String durum;
    private String takipNo;
    private String tarih;

}
