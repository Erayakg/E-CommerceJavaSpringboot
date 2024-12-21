package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;

public class SiparisDetayRequest {

    @NotNull(message = "Sipariş ID boş olamaz")
    private Long siparisId;

    @NotNull(message = "Sepet ID boş olamaz")
    private Long sepetId;

    @NotNull(message = "Ürün ID boş olamaz")
    private Long urunId;

    @NotNull(message = "Adet boş olamaz")
    private Integer adet;

    @NotNull(message = "Fiyat boş olamaz")
    private Integer fiyat;

    public Long getSiparisId() {
        return siparisId;
    }

    public void setSiparisId(Long siparisId) {
        this.siparisId = siparisId;
    }

    public Long getSepetId() {
        return sepetId;
    }

    public void setSepetId(Long sepetId) {
        this.sepetId = sepetId;
    }

    public Long getUrunId() {
        return urunId;
    }

    public void setUrunId(Long urunId) {
        this.urunId = urunId;
    }

    public Integer getAdet() {
        return adet;
    }

    public void setAdet(Integer adet) {
        this.adet = adet;
    }

    public Integer getFiyat() {
        return fiyat;
    }

    public void setFiyat(Integer fiyat) {
        this.fiyat = fiyat;
    }
}
