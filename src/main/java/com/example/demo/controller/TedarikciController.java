package com.example.demo.controller;

import com.example.demo.dto.TedarikciRequest;
import com.example.demo.dto.TedarikciRequest;
import com.example.demo.entites.Tedarikci;
import com.example.demo.entites.UrunTedarikci;
import com.example.demo.repositories.TedarikciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tedarikciler")
public class TedarikciController {

    @Autowired
    private TedarikciRepository tedarikciRepository;

    // -------------------------------------------------
    // 1) Entity -> DTO Dönüşümü
    // -------------------------------------------------
    private TedarikciRequest convertToDTO(Tedarikci entity) {
        TedarikciRequest dto = new TedarikciRequest();
        dto.setId(entity.getId());
        dto.setIsim(entity.getIsim());
        dto.setTelefon(entity.getTelefon());
        dto.setEmail(entity.getEmail());

        // UrunTedarikci listesi varsa, sadece ID'lerini tutuyoruz
        if (entity.getUrunTedarikciList() != null) {
            dto.setUrunTedarikciIds(entity.getUrunTedarikciList()
                    .stream()
                    .map(UrunTedarikci::getId)
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    // -------------------------------------------------
    // 2) DTO -> Entity Dönüşümü
    // -------------------------------------------------
    private Tedarikci convertToEntity(TedarikciRequest dto) {
        Tedarikci entity = new Tedarikci();
        entity.setId(dto.getId());
        entity.setIsim(dto.getIsim());
        entity.setTelefon(dto.getTelefon());
        entity.setEmail(dto.getEmail());
        // UrunTedarikci ilişkisini burada kontrol edeceğiz
        return entity;
    }

    // -------------------------------------------------
    // 3) GET - Tüm Tedarikçiler (DTO olarak)
    // -------------------------------------------------
    @GetMapping("/all")
    public ResponseEntity<List<TedarikciRequest>> getAllTedarikciler() {
        List<Tedarikci> tedarikciler = tedarikciRepository.findAll();
        List<TedarikciRequest> dtos = tedarikciler.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // -------------------------------------------------
    // 4) GET - ID ile Tek Tedarikçi
    // -------------------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<TedarikciRequest> getTedarikciById(@PathVariable Long id) {
        Optional<Tedarikci> tedarikciOpt = tedarikciRepository.findById(id);
        if (tedarikciOpt.isPresent()) {
            TedarikciRequest dto = convertToDTO(tedarikciOpt.get());
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    // -------------------------------------------------
    // 5) POST - Tedarikçi Ekle/Güncelle
    // -------------------------------------------------
    @PostMapping
    public ResponseEntity<TedarikciRequest> createOrUpdateTedarikci(@Valid @RequestBody TedarikciRequest dto) {
        Tedarikci entity;

        // ID varsa güncelleme, yoksa yeni kayıt
        if (dto.getId() != null) {
            Optional<Tedarikci> existingOpt = tedarikciRepository.findById(dto.getId());
            if (existingOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            entity = existingOpt.get();
            entity.setIsim(dto.getIsim());
            entity.setTelefon(dto.getTelefon());
            entity.setEmail(dto.getEmail());
        } else {
            entity = convertToEntity(dto);
        }

        // Kaydet ve DTO olarak dön
        Tedarikci saved = tedarikciRepository.save(entity);
        TedarikciRequest savedDto = convertToDTO(saved);
        return ResponseEntity.ok(savedDto);
    }

    // -------------------------------------------------
    // 6) DELETE - ID ile Silme
    // -------------------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTedarikci(@PathVariable Long id) {
        Optional<Tedarikci> tedarikciOpt = tedarikciRepository.findById(id);
        if (tedarikciOpt.isPresent()) {
            tedarikciRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
