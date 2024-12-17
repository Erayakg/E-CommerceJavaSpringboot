package com.example.demo.entites;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
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

    public int getToplamTutar() {
        return toplamTutar;
    }

    public void setToplamTutar(int toplamTutar) {
        this.toplamTutar = toplamTutar;
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

    public List<Iade> getIadeler() {
        return iadeler;
    }

    public void setIadeler(List<Iade> iadeler) {
        this.iadeler = iadeler;
    }

    public List<Kargo> getKargolar() {
        return kargolar;
    }

    public void setKargolar(List<Kargo> kargolar) {
        this.kargolar = kargolar;
    }

    public List<Odeme> getOdemeler() {
        return odemeler;
    }

    public void setOdemeler(List<Odeme> odemeler) {
        this.odemeler = odemeler;
    }
}
