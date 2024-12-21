package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;

public class SepetRequest {

    @NotNull(message = "Müşteri ID boş olamaz")
    private Long musteriId;

    @NotNull(message = "Tarih boş olamaz")
    private String tarih;

    public Long getMusteriId() {
        return musteriId;
    }

    public void setMusteriId(Long musteriId) {
        this.musteriId = musteriId;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }
}
