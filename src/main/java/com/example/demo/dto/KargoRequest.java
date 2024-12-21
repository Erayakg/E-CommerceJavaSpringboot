package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public class KargoRequest {

    @NotBlank(message = "Durum cannot be blank")
    private String durum;

    @NotBlank(message = "Takip No cannot be blank")
    private String takipNo;

    @NotBlank(message = "Tarih cannot be blank")
    private String tarih;

    private Long siparisId;

    // Getters and Setters
    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }

    public String getTakipNo() {
        return takipNo;
    }

    public void setTakipNo(String takipNo) {
        this.takipNo = takipNo;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public Long getSiparisId() {
        return siparisId;
    }

    public void setSiparisId(Long siparisId) {
        this.siparisId = siparisId;
    }
}
