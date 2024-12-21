package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public class SiparisRequest {

    @NotNull(message = "Müşteri ID boş olamaz")
    private Long musteriId;

    @NotNull(message = "Toplam tutar boş olamaz")
    private int toplamTutar;

    private String tarih;

    private List<Long> siparisDetayIdler;
    private List<Long> iadeIdler;
    private List<Long> kargoIdler;
    private List<Long> odemeIdler;

    // Getters and Setters
    public Long getMusteriId() {
        return musteriId;
    }

    public void setMusteriId(Long musteriId) {
        this.musteriId = musteriId;
    }

    public int getToplamTutar() {
        return toplamTutar;
    }

    public void setToplamTutar(int toplamTutar) {
        this.toplamTutar = toplamTutar;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public List<Long> getSiparisDetayIdler() {
        return siparisDetayIdler;
    }

    public void setSiparisDetayIdler(List<Long> siparisDetayIdler) {
        this.siparisDetayIdler = siparisDetayIdler;
    }

    public List<Long> getIadeIdler() {
        return iadeIdler;
    }

    public void setIadeIdler(List<Long> iadeIdler) {
        this.iadeIdler = iadeIdler;
    }

    public List<Long> getKargoIdler() {
        return kargoIdler;
    }

    public void setKargoIdler(List<Long> kargoIdler) {
        this.kargoIdler = kargoIdler;
    }

    public List<Long> getOdemeIdler() {
        return odemeIdler;
    }

    public void setOdemeIdler(List<Long> odemeIdler) {
        this.odemeIdler = odemeIdler;
    }
}
