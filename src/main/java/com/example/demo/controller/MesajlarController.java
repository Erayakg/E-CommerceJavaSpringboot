package com.example.demo.controller;

import com.example.demo.dto.MesajlarRequest;
import com.example.demo.dto.MesajlarRequest;
import com.example.demo.entites.Kullanici;
import com.example.demo.entites.Mesajlar;
import com.example.demo.repositories.KullaniciRepository;
import com.example.demo.repositories.MesajlarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/mesajlar")
public class MesajlarController {

    @Autowired
    private MesajlarRepository mesajlarRepository;

    @Autowired
    private KullaniciRepository kullaniciRepository;

    // -------------------------------------------------
    // 1) Entity -> DTO
    // -------------------------------------------------
    private MesajlarRequest convertToDTO(Mesajlar entity) {
        MesajlarRequest dto = new MesajlarRequest();
        dto.setId(entity.getId());
        dto.setMesaj(entity.getMesaj());
        dto.setTarih(entity.getTarih());

        if (entity.getKullanici() != null) {
            dto.setKullaniciId(entity.getKullanici().getId());
        }
        return dto;
    }

    // -------------------------------------------------
    // 2) DTO -> Entity
    // -------------------------------------------------
    private Mesajlar convertToEntity(MesajlarRequest dto) {
        Mesajlar entity = new Mesajlar();
        entity.setId(dto.getId());  // Güncellemede lazım olabilir
        entity.setMesaj(dto.getMesaj());
        entity.setTarih(dto.getTarih());
        // Kullanici set'ini controller metodunda yapacağız (repo'dan bulduktan sonra).
        return entity;
    }

    // -------------------------------------------------
    // 3) GET - Tüm Mesajlar (DTO olarak)
    // -------------------------------------------------
    @GetMapping("/all")
    public ResponseEntity<List<MesajlarRequest>> getAllMesajlar() {
        // İsterseniz findAllWithKullanici() da kullanabilirsiniz.
        List<Mesajlar> mesajlarList = mesajlarRepository.findAll();

        List<MesajlarRequest> dtos = mesajlarList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    // -------------------------------------------------
    // 4) GET - ID üzerinden Tek Mesaj (DTO)
    // -------------------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<MesajlarRequest> getMesajById(@PathVariable Long id) {
        Optional<Mesajlar> mesajOpt = mesajlarRepository.findById(id);
        if (mesajOpt.isPresent()) {
            MesajlarRequest dto = convertToDTO(mesajOpt.get());
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    // -------------------------------------------------
    // 5) POST - Mesaj Ekle / Güncelle (DTO alır, DTO döndürür)
    // -------------------------------------------------
    @PostMapping
    public ResponseEntity<MesajlarRequest> createOrUpdateMesaj(@Valid @RequestBody MesajlarRequest dto) {
        // 1) ID null ise yeni ekle, değilse güncelle
        Mesajlar entity;
        if (dto.getId() != null) {
            // Güncelleme
            Optional<Mesajlar> existingOpt = mesajlarRepository.findById(dto.getId());
            if (existingOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            entity = existingOpt.get();
            // mesaj, tarih güncellenir
            entity.setMesaj(dto.getMesaj());
            entity.setTarih(dto.getTarih());
        } else {
            // Yeni kayıt
            entity = convertToEntity(dto);
        }

        // 2) Kullanici baglantısı
        if (dto.getKullaniciId() != null) {
            Optional<Kullanici> kullaniciOpt = kullaniciRepository.findById(dto.getKullaniciId());
            if (kullaniciOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            entity.setKullanici(kullaniciOpt.get());
        } else {
            entity.setKullanici(null);
        }

        // 3) Kaydet
        Mesajlar saved = mesajlarRepository.save(entity);

        // 4) Geriye DTO olarak dön
        MesajlarRequest savedDTO = convertToDTO(saved);
        return ResponseEntity.ok(savedDTO);
    }

    // -------------------------------------------------
    // 6) DELETE - ID üzerinden Silme
    // -------------------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMesaj(@PathVariable Long id) {
        Optional<Mesajlar> mesajOpt = mesajlarRepository.findById(id);
        if (mesajOpt.isPresent()) {
            mesajlarRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
