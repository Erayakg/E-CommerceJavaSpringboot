package com.example.demo.entites;

import jakarta.persistence.*;

@Entity
@Table(name = "yoneticiler")
public class Yoneticiler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Burada Kullanici ile @OneToOne ilişkisi var.
     * Circle problemi DTO üzerinden çözüleceği için
     * ek anotasyon (@JsonIgnore, @JsonBackReference vs.) kullanmıyoruz.
     * DTO katmanı sayesinde döngü engellenecek.
     */
    @OneToOne
    @JoinColumn(name = "kullanici_id")
    private Kullanici kullanici;

    private String departman;

    // ===================== GETTER - SETTER =====================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Kullanici getKullanici() {
        return kullanici;
    }

    public void setKullanici(Kullanici kullanici) {
        this.kullanici = kullanici;
    }

    public String getDepartman() {
        return departman;
    }

    public void setDepartman(String departman) {
        this.departman = departman;
    }
}
