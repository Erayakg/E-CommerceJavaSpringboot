package com.example.demo.dto;

import java.util.List;

public class TedarikciRequest {
    private Long id;
    private String isim;
    private String telefon;
    private String email;
    private List<Long> urunTedarikciIds; // İlişkili ürünlerin ID'lerini tutuyoruz

    // ================== GETTER / SETTER ==================
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

    public List<Long> getUrunTedarikciIds() {
        return urunTedarikciIds;
    }

    public void setUrunTedarikciIds(List<Long> urunTedarikciIds) {
        this.urunTedarikciIds = urunTedarikciIds;
    }
}
