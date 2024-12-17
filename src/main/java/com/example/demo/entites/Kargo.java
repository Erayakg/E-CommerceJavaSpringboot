package com.example.demo.entites;

import jakarta.persistence.*;
import lombok.Data;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Siparis getSiparis() {
        return siparis;
    }

    public void setSiparis(Siparis siparis) {
        this.siparis = siparis;
    }

    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }

    public String getTakipNo() {
        return takipNo;
    }

    public void setTakipNo(String takipNo) {
        this.takipNo = takipNo;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }
}
