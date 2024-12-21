package com.example.demo.controller;

import com.example.demo.dto.UrunTedarikciRequest;
import com.example.demo.entites.Tedarikci;
import com.example.demo.entites.Urun;
import com.example.demo.entites.UrunTedarikci;
import com.example.demo.repositories.TedarikciRepository;
import com.example.demo.repositories.UrunRepository;
import com.example.demo.repositories.UrunTedarikciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/uruntedarikci")
public class UrunTedarikciController {

    @Autowired
    private UrunTedarikciRepository urunTedarikciRepository;

    @Autowired
    private UrunRepository urunRepository;

    @Autowired
    private TedarikciRepository tedarikciRepository;

    // -------------------------------------------------
    // 1) Entity -> DTO Dönüşümü
    // -------------------------------------------------
    private UrunTedarikciRequest convertToDTO(UrunTedarikci entity) {
        UrunTedarikciRequest dto = new UrunTedarikciRequest();
        dto.setId(entity.getId());

        if (entity.getUrun() != null) {
            dto.setUrunId(entity.getUrun().getId());
        }

        if (entity.getTedarikci() != null) {
            dto.setTedarikciId(entity.getTedarikci().getId());
        }

        return dto;
    }

    // -------------------------------------------------
    // 2) DTO -> Entity Dönüşümü
    // -------------------------------------------------
    private UrunTedarikci convertToEntity(UrunTedarikciRequest dto) {
        UrunTedarikci entity = new UrunTedarikci();

        entity.setId(dto.getId());

        // Urun ve Tedarikci ID'lerine göre ilişki kurma
        if (dto.getUrunId() != null) {
            Optional<Urun> urunOpt = urunRepository.findById(dto.getUrunId());
            urunOpt.ifPresent(entity::setUrun);
        }

        if (dto.getTedarikciId() != null) {
            Optional<Tedarikci> tedarikciOpt = tedarikciRepository.findById(dto.getTedarikciId());
            tedarikciOpt.ifPresent(entity::setTedarikci);
        }

        return entity;
    }

    // -------------------------------------------------
    // 3) GET - Tüm UrunTedarikci Kayıtları
    // -------------------------------------------------
    @GetMapping("/all")
    public ResponseEntity<List<UrunTedarikciRequest>> getAllUrunTedarikci() {
        List<UrunTedarikci> urunTedarikciList = urunTedarikciRepository.findAll();

        List<UrunTedarikciRequest> dtos = urunTedarikciList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    // -------------------------------------------------
    // 4) GET - ID ile Tek Kayıt
    // -------------------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<UrunTedarikciRequest> getUrunTedarikciById(@PathVariable Long id) {
        Optional<UrunTedarikci> urunTedarikciOpt = urunTedarikciRepository.findById(id);

        if (urunTedarikciOpt.isPresent()) {
            UrunTedarikciRequest dto = convertToDTO(urunTedarikciOpt.get());
            return ResponseEntity.ok(dto);
        }

        return ResponseEntity.notFound().build();
    }

    // -------------------------------------------------
    // 5) POST - Yeni Kayıt veya Güncelleme
    // -------------------------------------------------
    @PostMapping
    public ResponseEntity<UrunTedarikciRequest> createOrUpdateUrunTedarikci(@Valid @RequestBody UrunTedarikciRequest dto) {
        UrunTedarikci entity;

        if (dto.getId() != null) {
            Optional<UrunTedarikci> existingOpt = urunTedarikciRepository.findById(dto.getId());
            if (existingOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            entity = existingOpt.get();
        } else {
            entity = new UrunTedarikci();
        }

        // Urun ve Tedarikci set edilir
        entity = convertToEntity(dto);

        // Kaydedilen entity DTO'ya dönüştürülüp döndürülür
        UrunTedarikci saved = urunTedarikciRepository.save(entity);
        UrunTedarikciRequest savedDto = convertToDTO(saved);

        return ResponseEntity.ok(savedDto);
    }

    // -------------------------------------------------
    // 6) DELETE - ID ile Silme
    // -------------------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUrunTedarikci(@PathVariable Long id) {
        Optional<UrunTedarikci> urunTedarikciOpt = urunTedarikciRepository.findById(id);

        if (urunTedarikciOpt.isPresent()) {
            urunTedarikciRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
