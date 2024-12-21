package com.example.demo.controller;

import com.example.demo.dto.KategoriRequest;
import com.example.demo.entites.Kategori;
import com.example.demo.entites.Urun;
import com.example.demo.repositories.KategoriRepository;
import com.example.demo.repositories.UrunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/kategoriler")
public class KategoriController {

    @Autowired
    private KategoriRepository kategoriRepository;

    @Autowired
    private UrunRepository urunRepository;

    @GetMapping
    public List<Kategori> getAllKategoriler() {
        return kategoriRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kategori> getKategoriById(@PathVariable Long id) {
        Optional<Kategori> kategori = kategoriRepository.findById(id);
        return kategori.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Kategori createKategori(@RequestBody KategoriRequest kategoriRequest) {
        Kategori kategori = mapToKategori(kategoriRequest);
        return kategoriRepository.save(kategori);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kategori> updateKategori(@PathVariable Long id, @RequestBody KategoriRequest kategoriRequest) {
        Optional<Kategori> kategori = kategoriRepository.findById(id);
        if (kategori.isPresent()) {
            Kategori updatedKategori = mapToKategori(kategoriRequest);
            updatedKategori.setId(id);
            return ResponseEntity.ok(kategoriRepository.save(updatedKategori));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKategori(@PathVariable Long id) {
        Optional<Kategori> kategori = kategoriRepository.findById(id);
        if (kategori.isPresent()) {
            kategoriRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private Kategori mapToKategori(KategoriRequest kategoriRequest) {
        Kategori kategori = new Kategori();
        kategori.setIsim(kategoriRequest.getIsim());

        return kategori;
    }
}