package com.example.demo.controller;

import com.example.demo.dto.YoneticilerRequest;
import com.example.demo.dto.YoneticilerRequest;
import com.example.demo.entites.Kullanici;
import com.example.demo.entites.Yoneticiler;
import com.example.demo.repositories.KullaniciRepository;
import com.example.demo.repositories.YoneticilerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/yoneticiler")
public class YoneticilerController {

    @Autowired
    private YoneticilerRepository yoneticilerRepository;

    @Autowired
    private KullaniciRepository kullaniciRepository; // Kullanici baglantısı için

    // ------------------------------
    //  A) Dönüşüm Metotları
    // ------------------------------
    /**
     * Entity -> DTO
     */
    private YoneticilerRequest convertToDTO(Yoneticiler entity) {
        YoneticilerRequest dto = new YoneticilerRequest();
        dto.setId(entity.getId());
        dto.setDepartman(entity.getDepartman());

        if (entity.getKullanici() != null) {
            dto.setKullaniciId(entity.getKullanici().getId());
        }
        return dto;
    }

    /**
     * DTO -> Entity
     *  (Kullanici atamasını ise createOrUpdateYoneticiler metodunda yapabiliriz.)
     */
    private Yoneticiler convertToEntity(YoneticilerRequest dto) {
        Yoneticiler entity = new Yoneticiler();
        entity.setId(dto.getId()); // Güncellemede gerekebilir
        entity.setDepartman(dto.getDepartman());

        // Kullanici set etme işi genelde Controller'da (repo'dan bulunduktan sonra) yapılır.
        // Burada direkt set edebilirsiniz ama Kullanici'yı DB'den bulmak gerekir.
        // O kısmı createOrUpdate metodunda yapacağız.
        return entity;
    }

    // ------------------------------
    //  B) GET - Tüm Yoneticiler (DTO olarak)
    // ------------------------------
    @GetMapping("/all")
    public ResponseEntity<List<YoneticilerRequest>> getAllYoneticiler() {
        // Eager fetch veya Lazy fetch fark etmez,
        // dileyen findAllWithKullanici() da çağırabilir
        List<Yoneticiler> yoneticilerList = yoneticilerRepository.findAll();

        // Entity -> DTO
        List<YoneticilerRequest> result = yoneticilerList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }

    // ------------------------------
    //  C) GET - ID ile Tek Yoneticiler (DTO olarak)
    // ------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<YoneticilerRequest> getYoneticilerById(@PathVariable Long id) {
        Optional<Yoneticiler> yoneticilerOpt = yoneticilerRepository.findById(id);
        if (yoneticilerOpt.isPresent()) {
            YoneticilerRequest dto = convertToDTO(yoneticilerOpt.get());
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    // ------------------------------
    //  D) POST/PUT - Yonetici Oluştur/Güncelle (DTO alır, DTO döndürür)
    // ------------------------------
    @PostMapping
    public ResponseEntity<YoneticilerRequest> createOrUpdateYoneticiler(
            @Valid @RequestBody YoneticilerRequest dto
    ) {
        // 1) ID var mı yok mu diye check edip create/update karar verelim
        Yoneticiler entity;
        if (dto.getId() != null) {
            // Güncelleme
            Optional<Yoneticiler> existingOpt = yoneticilerRepository.findById(dto.getId());
            if (existingOpt.isEmpty()) {
                // ID gönderilmiş ama DB'de yoksa
                return ResponseEntity.notFound().build();
            }
            entity = existingOpt.get();
            // sadece güncellenecek alanları set et
            entity.setDepartman(dto.getDepartman());
        } else {
            // Yeni kayıt
            entity = convertToEntity(dto);
        }

        // 2) Kullanici baglantısı
        if (dto.getKullaniciId() != null) {
            Optional<Kullanici> kullaniciOpt = kullaniciRepository.findById(dto.getKullaniciId());
            if (kullaniciOpt.isEmpty()) {
                // Kullanici yoksa 404
                return ResponseEntity.notFound().build();
            }
            entity.setKullanici(kullaniciOpt.get());
        } else {
            entity.setKullanici(null);
        }

        // 3) DB'ye kaydet
        Yoneticiler saved = yoneticilerRepository.save(entity);

        // 4) Kaydedilmiş entity'yi DTO'ya dönüştürüp geri gönder
        YoneticilerRequest savedDTO = convertToDTO(saved);
        return ResponseEntity.ok(savedDTO);
    }

    // ------------------------------
    //  E) DELETE - ID ile Silme
    // ------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteYoneticiler(@PathVariable Long id) {
        Optional<Yoneticiler> yoneticilerOpt = yoneticilerRepository.findById(id);
        if (yoneticilerOpt.isPresent()) {
            yoneticilerRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
