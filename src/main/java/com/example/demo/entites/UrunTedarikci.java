package com.example.demo.entites;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "uruntedarikci")
public class UrunTedarikci {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "urun_id")
    private Urun urun;

    @ManyToOne
    @JoinColumn(name = "tedarikci_id")
    private Tedarikci tedarikci;

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

    public Tedarikci getTedarikci() {
        return tedarikci;
    }

    public void setTedarikci(Tedarikci tedarikci) {
        this.tedarikci = tedarikci;
    }
}
