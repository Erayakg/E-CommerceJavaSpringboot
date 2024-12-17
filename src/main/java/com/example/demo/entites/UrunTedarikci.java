package com.example.demo.entites;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "uruntedarikci")
public class UrunTedarikci {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "urun_id")
    private Urun urun;

    @ManyToOne
    @JoinColumn(name = "tedarikci_id")
    private Tedarikci tedarikci;

    // Getters and Setters
}
