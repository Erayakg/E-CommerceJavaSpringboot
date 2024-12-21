package com.example.demo.dto;

public class UrunTedarikciRequest {
    private Long id;
    private Long urunId;      // Urun nesnesi yerine sadece ID
    private Long tedarikciId; // Tedarikci nesnesi yerine sadece ID

    // ================== GETTER / SETTER ==================
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUrunId() {
        return urunId;
    }

    public void setUrunId(Long urunId) {
        this.urunId = urunId;
    }

    public Long getTedarikciId() {
        return tedarikciId;
    }

    public void setTedarikciId(Long tedarikciId) {
        this.tedarikciId = tedarikciId;
    }
}
