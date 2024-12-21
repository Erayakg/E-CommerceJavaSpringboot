package com.example.demo.controller;

import com.example.demo.dto.SiparisDetayRequest;
import com.example.demo.entites.Siparis;
import com.example.demo.entites.Sepet;
import com.example.demo.entites.Urun;
import com.example.demo.entites.SiparisDetay;
import com.example.demo.repositories.SiparisRepository;
import com.example.demo.repositories.SepetRepository;
import com.example.demo.repositories.UrunRepository;
import com.example.demo.repositories.SiparisDetayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/siparisdetay")
public class SiparisDetayController {

    @Autowired
    private SiparisDetayRepository siparisDetayRepository;

    @Autowired
    private SiparisRepository siparisRepository;

    @Autowired
    private SepetRepository sepetRepository;

    @Autowired
    private UrunRepository urunRepository;

    @GetMapping
    public List<SiparisDetay> getAllSiparisDetay() {
        return siparisDetayRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SiparisDetay> getSiparisDetayById(@PathVariable Long id) {
        Optional<SiparisDetay> siparisDetay = siparisDetayRepository.findById(id);
        return siparisDetay.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SiparisDetay> createSiparisDetay(@RequestBody SiparisDetayRequest siparisDetayRequest) {
        SiparisDetay siparisDetay = mapToSiparisDetay(siparisDetayRequest);
        if (siparisDetay == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(siparisDetayRepository.save(siparisDetay));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SiparisDetay> updateSiparisDetay(@PathVariable Long id, @RequestBody SiparisDetayRequest siparisDetayRequest) {
        Optional<SiparisDetay> existingSiparisDetay = siparisDetayRepository.findById(id);
        if (existingSiparisDetay.isPresent()) {
            SiparisDetay updatedSiparisDetay = mapToSiparisDetay(siparisDetayRequest);
            if (updatedSiparisDetay == null) {
                return ResponseEntity.badRequest().build();
            }
            updatedSiparisDetay.setId(id);
            return ResponseEntity.ok(siparisDetayRepository.save(updatedSiparisDetay));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSiparisDetay(@PathVariable Long id) {
        Optional<SiparisDetay> siparisDetay = siparisDetayRepository.findById(id);
        if (siparisDetay.isPresent()) {
            siparisDetayRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private SiparisDetay mapToSiparisDetay(SiparisDetayRequest siparisDetayRequest) {
        Optional<Siparis> siparis = siparisRepository.findById(siparisDetayRequest.getSiparisId());
        Optional<Sepet> sepet = sepetRepository.findById(siparisDetayRequest.getSepetId());
        Optional<Urun> urun = urunRepository.findById(siparisDetayRequest.getUrunId());

        if (siparis.isEmpty() || sepet.isEmpty() || urun.isEmpty()) {
            return null;
        }

        SiparisDetay siparisDetay = new SiparisDetay();
        siparisDetay.setSiparis(siparis.get());
        siparisDetay.setSepet(sepet.get());
        siparisDetay.setUrun(urun.get());
        siparisDetay.setAdet(siparisDetayRequest.getAdet());
        siparisDetay.setFiyat(siparisDetayRequest.getFiyat());

        return siparisDetay;
    }
}
