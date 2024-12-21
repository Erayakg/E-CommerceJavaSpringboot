package com.example.demo.controller;

import com.example.demo.dto.SepetRequest;
import com.example.demo.entites.Musteri;
import com.example.demo.entites.Sepet;
import com.example.demo.repositories.MusteriRepository;
import com.example.demo.repositories.SepetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sepet")
public class SepetController {

    @Autowired
    private SepetRepository sepetRepository;

    @Autowired
    private MusteriRepository musteriRepository;

    @GetMapping
    public List<Sepet> getAllSepet() {
        return sepetRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sepet> getSepetById(@PathVariable Long id) {
        Optional<Sepet> sepet = sepetRepository.findById(id);
        return sepet.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Sepet> createSepet(@RequestBody SepetRequest sepetRequest) {
        Optional<Musteri> musteri = musteriRepository.findById(sepetRequest.getMusteriId());
        if (musteri.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Sepet sepet = new Sepet();
        sepet.setMusteri(musteri.get());
        sepet.setTarih(sepetRequest.getTarih());

        return ResponseEntity.ok(sepetRepository.save(sepet));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sepet> updateSepet(@PathVariable Long id, @RequestBody SepetRequest sepetRequest) {
        Optional<Sepet> existingSepet = sepetRepository.findById(id);
        if (existingSepet.isPresent()) {
            Optional<Musteri> musteri = musteriRepository.findById(sepetRequest.getMusteriId());
            if (musteri.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            Sepet updatedSepet = existingSepet.get();
            updatedSepet.setMusteri(musteri.get());
            updatedSepet.setTarih(sepetRequest.getTarih());

            return ResponseEntity.ok(sepetRepository.save(updatedSepet));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSepet(@PathVariable Long id) {
        Optional<Sepet> sepet = sepetRepository.findById(id);
        if (sepet.isPresent()) {
            sepetRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
