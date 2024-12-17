package com.example.demo.entites;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;


@Entity
@Table(name = "kullanici")
public class Kullanici {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ad;
    private String soyad;
    private String email;
    private String sifre;
    private String rol;

    @OneToMany(mappedBy = "kullanici")
    private List<Bildirim> bildirimler;

    @OneToMany(mappedBy = "kullanici")
    private List<Degerlendirme> degerlendirmeler;

    @OneToMany(mappedBy = "kullanici")
    private List<Favoriler> favoriler;

    @OneToMany(mappedBy = "kullanici")
    private List<Mesajlar> mesajlar;

    @OneToOne(mappedBy = "kullanici")
    private Musteri musteri;

    @OneToOne(mappedBy = "kullanici")
    private Yoneticiler yonetici;

    @OneToMany(mappedBy = "kullanici")
    private List<Adres> adresler;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<Bildirim> getBildirimler() {
        return bildirimler;
    }

    public void setBildirimler(List<Bildirim> bildirimler) {
        this.bildirimler = bildirimler;
    }

    public List<Degerlendirme> getDegerlendirmeler() {
        return degerlendirmeler;
    }

    public void setDegerlendirmeler(List<Degerlendirme> degerlendirmeler) {
        this.degerlendirmeler = degerlendirmeler;
    }

    public List<Favoriler> getFavoriler() {
        return favoriler;
    }

    public void setFavoriler(List<Favoriler> favoriler) {
        this.favoriler = favoriler;
    }

    public List<Mesajlar> getMesajlar() {
        return mesajlar;
    }

    public void setMesajlar(List<Mesajlar> mesajlar) {
        this.mesajlar = mesajlar;
    }

    public Musteri getMusteri() {
        return musteri;
    }

    public void setMusteri(Musteri musteri) {
        this.musteri = musteri;
    }

    public Yoneticiler getYonetici() {
        return yonetici;
    }

    public void setYonetici(Yoneticiler yonetici) {
        this.yonetici = yonetici;
    }

    public List<Adres> getAdresler() {
        return adresler;
    }

    public void setAdresler(List<Adres> adresler) {
        this.adresler = adresler;
    }
}
