package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

public class KategoriRequest {

    @NotBlank(message = "Isim cannot be blank")
    private String isim;

    private List<Long> urunIdler;

    // Getters and Setters
    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public List<Long> getUrunIdler() {
        return urunIdler;
    }

    public void setUrunIdler(List<Long> urunIdler) {
        this.urunIdler = urunIdler;
    }
}