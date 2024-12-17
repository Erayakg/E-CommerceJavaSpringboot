package com.example.demo.entites;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sepet")
public class Sepet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "musteri_id")
    private Musteri musteri;

    private String tarih;

    @OneToMany(mappedBy = "sepet")
    private List<SiparisDetay> siparisDetaylar;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Musteri getMusteri() {
        return musteri;
    }

    public void setMusteri(Musteri musteri) {
        this.musteri = musteri;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public List<SiparisDetay> getSiparisDetaylar() {
        return siparisDetaylar;
    }

    public void setSiparisDetaylar(List<SiparisDetay> siparisDetaylar) {
        this.siparisDetaylar = siparisDetaylar;
    }
}
