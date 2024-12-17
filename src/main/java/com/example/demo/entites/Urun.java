package com.example.demo.entites;
import jakarta.persistence.*;
import lombok.Data;
import jakarta.persistence.*;
import java.util.List;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getFiyat() {
        return fiyat;
    }

    public void setFiyat(String fiyat) {
        this.fiyat = fiyat;
    }

    public Integer getStok() {
        return stok;
    }

    public void setStok(Integer stok) {
        this.stok = stok;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
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

    public List<Indirim> getIndirimler() {
        return indirimler;
    }

    public void setIndirimler(List<Indirim> indirimler) {
        this.indirimler = indirimler;
    }

    public List<SiparisDetay> getSiparisDetaylar() {
        return siparisDetaylar;
    }

    public void setSiparisDetaylar(List<SiparisDetay> siparisDetaylar) {
        this.siparisDetaylar = siparisDetaylar;
    }

    public List<UrunTedarikci> getUrunTedarikciList() {
        return urunTedarikciList;
    }

    public void setUrunTedarikciList(List<UrunTedarikci> urunTedarikciList) {
        this.urunTedarikciList = urunTedarikciList;
    }
}
