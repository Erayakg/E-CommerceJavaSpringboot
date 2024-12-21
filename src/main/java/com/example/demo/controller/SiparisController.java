package com.example.demo.controller;

import com.example.demo.dto.SiparisRequest;
import com.example.demo.entites.Musteri;
import com.example.demo.entites.Siparis;
import com.example.demo.repositories.MusteriRepository;
import com.example.demo.repositories.SiparisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/siparisler")
public class SiparisController {

    @Autowired
    private SiparisRepository siparisRepository;

    @Autowired
    private MusteriRepository musteriRepository;

    @GetMapping
    public ResponseEntity<?> getAllSiparisler() {
        return ResponseEntity.ok(siparisRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSiparisById(@PathVariable Long id) {
        Optional<Siparis> siparis = siparisRepository.findById(id);
        return siparis.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createSiparis(@RequestBody SiparisRequest siparisRequest) {
        Siparis siparis = mapToSiparis(siparisRequest);
        return ResponseEntity.ok(siparisRepository.save(siparis));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSiparis(@PathVariable Long id, @RequestBody SiparisRequest siparisRequest) {
        Optional<Siparis> existingSiparis = siparisRepository.findById(id);
        if (existingSiparis.isPresent()) {
            Siparis updatedSiparis = mapToSiparis(siparisRequest);
            updatedSiparis.setId(id);
            return ResponseEntity.ok(siparisRepository.save(updatedSiparis));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSiparis(@PathVariable Long id) {
        Optional<Siparis> siparis = siparisRepository.findById(id);
        if (siparis.isPresent()) {
            siparisRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private Siparis mapToSiparis(SiparisRequest siparisRequest) {
        Siparis siparis = new Siparis();
        Optional<Musteri> musteri = musteriRepository.findById(siparisRequest.getMusteriId());
        musteri.ifPresent(siparis::setMusteri);

        siparis.setToplamTutar(siparisRequest.getToplamTutar());
        siparis.setTarih(siparisRequest.getTarih());
        // Daha fazla ilişki (siparisDetaylar, iadeler vs.) için gerekli mapping yapılabilir.
        return siparis;
    }
}
