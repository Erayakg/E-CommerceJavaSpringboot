package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MusteriRequest {

    @NotNull(message = "Kullanici ID boş olamaz")
    private Long kullaniciId;

    @NotBlank(message = "Telefon numarası boş olamaz")
    private String telefon;

    public Long getKullaniciId() {
        return kullaniciId;
    }

    public void setKullaniciId(Long kullaniciId) {
        this.kullaniciId = kullaniciId;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
}
