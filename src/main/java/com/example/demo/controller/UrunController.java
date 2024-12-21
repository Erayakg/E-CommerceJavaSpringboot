package com.example.demo.controller;

import com.example.demo.dto.UrunRequest;
import com.example.demo.entites.Urun;
import com.example.demo.entites.Kategori;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/urunler")
public class UrunController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/urunler")
    public List<Urun> getAllUrunler() {
        return entityManager.createQuery("SELECT u FROM Urun u inner join  Kategori on Kategori.id=u.kategori.id", Urun.class)

                .getResultList();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Urun> getUrunById(@PathVariable Long id) {
        Urun urun = entityManager.find(Urun.class, id);
        return urun != null ? ResponseEntity.ok(urun) : ResponseEntity.notFound().build();
    }
    @Transactional
    @PostMapping
    public Urun createUrun(@RequestBody UrunRequest urunRequest) {
        Urun urun = mapToUrun(urunRequest);
        entityManager.persist(urun);
        return urun;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Urun> updateUrun(@PathVariable Long id, @RequestBody UrunRequest urunRequest) {
        Urun existingUrun = entityManager.find(Urun.class, id);
        if (existingUrun != null) {
            Urun updatedUrun = mapToUrun(urunRequest);
            updatedUrun.setId(id);
            entityManager.merge(updatedUrun);
            return ResponseEntity.ok(updatedUrun);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUrun(@PathVariable Long id) {
        Urun urun = entityManager.find(Urun.class, id);
        if (urun != null) {
            entityManager.remove(urun);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private Urun mapToUrun(UrunRequest urunRequest) {
        Urun urun = new Urun();
        urun.setIsim(urunRequest.getIsim());
        urun.setFiyat(urunRequest.getFiyat());
        urun.setStok(urunRequest.getStok());
        if (urunRequest.getKategoriId() != null) {
            Kategori kategori = entityManager.find(Kategori.class, urunRequest.getKategoriId());
            urun.setKategori(kategori);
        }
        return urun;
    }
}