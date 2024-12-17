package com.example.demo.dto;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class KullaniciRequest {

    @NotBlank(message = "Ad cannot be blank")
    private String ad;

    @NotBlank(message = "Soyad cannot be blank")
    private String soyad;

    @Email(message = "Invalid email address")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    private String sifre;

    @NotBlank(message = "Rol cannot be blank")
    private String rol;

    // Getters and Setters
    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
