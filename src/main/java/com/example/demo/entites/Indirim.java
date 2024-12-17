package com.example.demo.entites;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "indirim")
public class Indirim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "urun_id")
    private Urun urun;

    private Integer yuzde;
    private String baslangicTarihi;
    private String bitisTarihi;

}
