package com.example.demo.entites;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
@Entity
@Table(name = "siparis")
public class Siparis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "musteri_id")
    private Musteri musteri;

    private int toplamTutar;

    private String tarih;

    @OneToMany(mappedBy = "siparis")
    private List<SiparisDetay> siparisDetaylar;

    @OneToMany(mappedBy = "siparis")
    private List<Iade> iadeler;

    @OneToMany(mappedBy = "siparis")
    private List<Kargo> kargolar;

    @OneToMany(mappedBy = "siparis")
    private List<Odeme> odemeler;

}
