package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public class UrunRequest {

    @NotBlank(message = "Isim cannot be blank")
    private String isim;

    private String fiyat;
    private Integer stok;
    private Long kategoriId;

    // Getters and Setters
    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getFiyat() {
        return fiyat;
    }

    public void setFiyat(String fiyat) {
        this.fiyat = fiyat;
    }

    public Integer getStok() {
        return stok;
    }

    public void setStok(Integer stok) {
        this.stok = stok;
    }

    public Long getKategoriId() {
        return kategoriId;
    }

    public void setKategoriId(Long kategoriId) {
        this.kategoriId = kategoriId;
    }
}