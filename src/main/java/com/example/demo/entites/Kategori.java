package com.example.demo.entites;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.persistence.*;
import java.util.List;
@Data
@Entity
@Table(name = "kategori")
public class Kategori {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String isim;

    @OneToMany(mappedBy = "kategori")
    private List<Urun> urunler;

}
