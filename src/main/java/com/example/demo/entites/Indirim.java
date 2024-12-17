package com.example.demo.entites;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "indirim")
public class Indirim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "urun_id")
    private Urun urun;

    private Integer yuzde;
    private String baslangicTarihi;
    private String bitisTarihi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Urun getUrun() {
        return urun;
    }

    public void setUrun(Urun urun) {
        this.urun = urun;
    }

    public Integer getYuzde() {
        return yuzde;
    }

    public void setYuzde(Integer yuzde) {
        this.yuzde = yuzde;
    }

    public String getBaslangicTarihi() {
        return baslangicTarihi;
    }

    public void setBaslangicTarihi(String baslangicTarihi) {
        this.baslangicTarihi = baslangicTarihi;
    }

    public String getBitisTarihi() {
        return bitisTarihi;
    }

    public void setBitisTarihi(String bitisTarihi) {
        this.bitisTarihi = bitisTarihi;
    }
}
