package com.example.demo.entites;
import jakarta.persistence.*;
import lombok.Data;
import jakarta.persistence.*;
import java.util.List;
import jakarta.persistence.*;
import java.util.List;
@Entity
@Data
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

}
