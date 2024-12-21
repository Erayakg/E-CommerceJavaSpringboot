package com.example.demo.controller;

import com.example.demo.dto.IndirimRequest;
import com.example.demo.entites.Indirim;
import com.example.demo.entites.Urun;
import com.example.demo.repositories.IndirimRepository;
import com.example.demo.repositories.UrunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/indirim")
public class IndirimController {

    @Autowired
    private IndirimRepository indirimRepository;

    @Autowired
    private UrunRepository urunRepository;

    @GetMapping
    public List<Indirim> getAllIndirim() {
        return indirimRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Indirim> getIndirimById(@PathVariable Long id) {
        Optional<Indirim> indirim = indirimRepository.findById(id);
        return indirim.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Indirim> createIndirim(@RequestBody IndirimRequest indirimRequest) {
        Optional<Urun> urun = urunRepository.findById(indirimRequest.getUrunId());
        if (urun.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Indirim indirim = new Indirim();
        indirim.setUrun(urun.get());
        indirim.setYuzde(indirimRequest.getYuzde());
        indirim.setBaslangicTarihi(indirimRequest.getBaslangicTarihi());
        indirim.setBitisTarihi(indirimRequest.getBitisTarihi());

        return ResponseEntity.ok(indirimRepository.save(indirim));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Indirim> updateIndirim(@PathVariable Long id, @RequestBody IndirimRequest indirimRequest) {
        Optional<Indirim> existingIndirim = indirimRepository.findById(id);
        if (existingIndirim.isPresent()) {
            Optional<Urun> urun = urunRepository.findById(indirimRequest.getUrunId());
            if (urun.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            Indirim updatedIndirim = existingIndirim.get();
            updatedIndirim.setUrun(urun.get());
            updatedIndirim.setYuzde(indirimRequest.getYuzde());
            updatedIndirim.setBaslangicTarihi(indirimRequest.getBaslangicTarihi());
            updatedIndirim.setBitisTarihi(indirimRequest.getBitisTarihi());

            return ResponseEntity.ok(indirimRepository.save(updatedIndirim));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIndirim(@PathVariable Long id) {
        Optional<Indirim> indirim = indirimRepository.findById(id);
        if (indirim.isPresent()) {
            indirimRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
