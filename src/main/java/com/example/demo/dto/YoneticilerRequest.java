package com.example.demo.dto;

public class YoneticilerRequest {
    private Long id;
    private Long kullaniciId;  // Kullanici nesnesinin tamamı yerine sadece ID
    private String departman;

    // ===================== GETTER - SETTER =====================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getKullaniciId() {
        return kullaniciId;
    }

    public void setKullaniciId(Long kullaniciId) {
        this.kullaniciId = kullaniciId;
    }

    public String getDepartman() {
        return departman;
    }

    public void setDepartman(String departman) {
        this.departman = departman;
    }
}
