package com.example.demo.entites;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
@Table(name = "adres")
public class Adres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "kullanici_id")
    private Kullanici kullanici;

    private String sehir;
    private String ilce;
    private String mahalle;
    private String detay;

}
