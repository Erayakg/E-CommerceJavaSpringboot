package com.example.demo.entites;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "tedarikci")
public class Tedarikci {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String isim;
    private String telefon;
    private String email;

    @OneToMany(mappedBy = "tedarikci")
    private List<UrunTedarikci> urunTedarikciList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<UrunTedarikci> getUrunTedarikciList() {
        return urunTedarikciList;
    }

    public void setUrunTedarikciList(List<UrunTedarikci> urunTedarikciList) {
        this.urunTedarikciList = urunTedarikciList;
    }
}
