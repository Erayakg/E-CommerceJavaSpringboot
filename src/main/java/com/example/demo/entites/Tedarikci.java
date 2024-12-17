package com.example.demo.entites;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
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

}
