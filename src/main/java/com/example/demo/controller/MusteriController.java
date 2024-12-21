package com.example.demo.controller;

import com.example.demo.dto.MusteriRequest;
import com.example.demo.entites.Kullanici;
import com.example.demo.entites.Musteri;
import com.example.demo.repositories.KullaniciRepository;
import com.example.demo.repositories.MusteriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/musteriler")
public class MusteriController {

    @Autowired
    private MusteriRepository musteriRepository;

    @Autowired
    private KullaniciRepository kullaniciRepository;

    @GetMapping
    public List<Musteri> getAllMusteriler() {
        return musteriRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Musteri> getMusteriById(@PathVariable Long id) {
        Optional<Musteri> musteri = musteriRepository.findById(id);
        return musteri.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Musteri> createMusteri(@RequestBody MusteriRequest musteriRequest) {
        Musteri musteri = mapToMusteri(musteriRequest);
        if (musteri == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(musteriRepository.save(musteri));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Musteri> updateMusteri(@PathVariable Long id, @RequestBody MusteriRequest musteriRequest) {
        Optional<Musteri> existingMusteri = musteriRepository.findById(id);
        if (existingMusteri.isPresent()) {
            Musteri updatedMusteri = mapToMusteri(musteriRequest);
            if (updatedMusteri == null) {
                return ResponseEntity.badRequest().build();
            }
            updatedMusteri.setId(id);
            return ResponseEntity.ok(musteriRepository.save(updatedMusteri));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMusteri(@PathVariable Long id) {
        Optional<Musteri> musteri = musteriRepository.findById(id);
        if (musteri.isPresent()) {
            musteriRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private Musteri mapToMusteri(MusteriRequest musteriRequest) {
        Optional<Kullanici> kullanici = kullaniciRepository.findById(musteriRequest.getKullaniciId());
        if (kullanici.isEmpty()) {
            return null;
        }
        Musteri musteri = new Musteri();
        musteri.setKullanici(kullanici.get());
        musteri.setTelefon(musteriRequest.getTelefon());
        return musteri;
    }
}
