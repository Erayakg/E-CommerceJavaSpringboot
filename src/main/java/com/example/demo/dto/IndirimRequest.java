package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;

public class IndirimRequest {

    @NotNull(message = "Ürün ID boş olamaz")
    private Long urunId;

    @NotNull(message = "Yüzde boş olamaz")
    private Integer yuzde;

    @NotNull(message = "Başlangıç tarihi boş olamaz")
    private String baslangicTarihi;

    @NotNull(message = "Bitiş tarihi boş olamaz")
    private String bitisTarihi;

    public Long getUrunId() {
        return urunId;
    }

    public void setUrunId(Long urunId) {
        this.urunId = urunId;
    }

    public Integer getYuzde() {
        return yuzde;
    }

    public void setYuzde(Integer yuzde) {
        this.yuzde = yuzde;
    }

    public String getBaslangicTarihi() {
        return baslangicTarihi;
    }

    public void setBaslangicTarihi(String baslangicTarihi) {
        this.baslangicTarihi = baslangicTarihi;
    }

    public String getBitisTarihi() {
        return bitisTarihi;
    }

    public void setBitisTarihi(String bitisTarihi) {
        this.bitisTarihi = bitisTarihi;
    }
}
