package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;

public class OdemeRequest {

    @NotNull(message = "Sipariş ID boş olamaz")
    private Long siparisId;

    @NotNull(message = "Tutar boş olamaz")
    private int tutar;

    @NotNull(message = "Tarih boş olamaz")
    private String tarih;

    public Long getSiparisId() {
        return siparisId;
    }

    public void setSiparisId(Long siparisId) {
        this.siparisId = siparisId;
    }

    public int getTutar() {
        return tutar;
    }

    public void setTutar(int tutar) {
        this.tutar = tutar;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }
}
