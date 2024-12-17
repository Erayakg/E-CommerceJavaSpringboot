package com.example.demo.entites;
import jakarta.persistence.*;
import lombok.Data;
import jakarta.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "urun")
public class Urun {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String isim;
    private String  fiyat;
    private Integer stok;

    @ManyToOne
    @JoinColumn(name = "kategori_id")
    private Kategori kategori;

    @OneToMany(mappedBy = "urun")
    private List<Degerlendirme> degerlendirmeler;

    @OneToMany(mappedBy = "urun")
    private List<Favoriler> favoriler;

    @OneToMany(mappedBy = "urun")
    private List<Indirim> indirimler;

    @OneToMany(mappedBy = "urun")
    private List<SiparisDetay> siparisDetaylar;

    @OneToMany(mappedBy = "urun")
    private List<UrunTedarikci> urunTedarikciList;

    // Getters and Setters
}
