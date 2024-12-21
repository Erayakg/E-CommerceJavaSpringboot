package com.example.demo.controller;

import com.example.demo.dto.OdemeRequest;
import com.example.demo.entites.Odeme;
import com.example.demo.entites.Siparis;
import com.example.demo.repositories.OdemeRepository;
import com.example.demo.repositories.SiparisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/odeme")
public class OdemeController {

    @Autowired
    private OdemeRepository odemeRepository;

    @Autowired
    private SiparisRepository siparisRepository;

    @GetMapping
    public List<Odeme> getAllOdeme() {
        return odemeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odeme> getOdemeById(@PathVariable Long id) {
        Optional<Odeme> odeme = odemeRepository.findById(id);
        return odeme.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Odeme> createOdeme(@RequestBody OdemeRequest odemeRequest) {
        Optional<Siparis> siparis = siparisRepository.findById(odemeRequest.getSiparisId());
        if (siparis.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Odeme odeme = new Odeme();
        odeme.setSiparis(siparis.get());
        odeme.setTutar(odemeRequest.getTutar());
        odeme.setTarih(odemeRequest.getTarih());

        return ResponseEntity.ok(odemeRepository.save(odeme));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Odeme> updateOdeme(@PathVariable Long id, @RequestBody OdemeRequest odemeRequest) {
        Optional<Odeme> existingOdeme = odemeRepository.findById(id);
        if (existingOdeme.isPresent()) {
            Optional<Siparis> siparis = siparisRepository.findById(odemeRequest.getSiparisId());
            if (siparis.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            Odeme updatedOdeme = existingOdeme.get();
            updatedOdeme.setSiparis(siparis.get());
            updatedOdeme.setTutar(odemeRequest.getTutar());
            updatedOdeme.setTarih(odemeRequest.getTarih());

            return ResponseEntity.ok(odemeRepository.save(updatedOdeme));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOdeme(@PathVariable Long id) {
        Optional<Odeme> odeme = odemeRepository.findById(id);
        if (odeme.isPresent()) {
            odemeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
