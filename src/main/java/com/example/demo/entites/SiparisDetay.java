package com.example.demo.entites;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "siparisdetay")
public class SiparisDetay {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "siparis_id")
    private Siparis siparis;

    @ManyToOne
    @JoinColumn(name = "sepet_id")
    private Sepet sepet;

    @ManyToOne
    @JoinColumn(name = "urun_id")
    private Urun urun;

    private Integer adet;
    private int fiyat;

}
