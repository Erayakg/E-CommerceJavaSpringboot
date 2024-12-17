package com.example.demo.controller;

import com.example.demo.dto.KullaniciRequest;
import com.example.demo.entites.Kullanici;
import com.example.demo.repositories.KullaniciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/kullanici")
public class KullaniciController {

    @Autowired
    private KullaniciRepository kullaniciRepository;

    // Kullanıcıyı email adresi ile bulma
    @GetMapping("/email/{email}")
    public ResponseEntity<Kullanici> getKullaniciByEmail(@PathVariable String email) {
        Optional<Kullanici> kullanici = kullaniciRepository.findByEmail(email);
        return kullanici.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Tüm kullanıcıları ve mesajları ilişkilendirilmiş olarak getirme
    @GetMapping("/with-mesajlar")
    public ResponseEntity<List<Kullanici>> getAllKullaniciWithMesajlar() {
        List<Kullanici> kullaniciList = kullaniciRepository.findAllWithMesajlar();
        return ResponseEntity.ok(kullaniciList);
    }

    // Tüm kullanıcıları ve favorileri ilişkilendirilmiş olarak getirme
    @GetMapping("/with-favoriler")
    public ResponseEntity<List<Kullanici>> getAllKullaniciWithFavoriler() {
        List<Kullanici> kullaniciList = kullaniciRepository.findAllWithFavoriler();
        return ResponseEntity.ok(kullaniciList);
    }

    // Tüm kullanıcıları ve adresleri ilişkilendirilmiş olarak getirme
    @GetMapping("/with-adresler")
    public ResponseEntity<List<Kullanici>> getAllKullaniciWithAdresler() {
        List<Kullanici> kullaniciList = kullaniciRepository.findAllWithAdresler();
        return ResponseEntity.ok(kullaniciList);
    }

    // Kullanıcıyı eklemek veya güncellemek
    @PostMapping
    public ResponseEntity<Kullanici> createOrUpdateKullanici(@Valid @RequestBody KullaniciRequest kullaniciRequest) {
        Kullanici kullanici = new Kullanici();
        kullanici.setAd(kullaniciRequest.getAd());
        kullanici.setSoyad(kullaniciRequest.getSoyad());
        kullanici.setEmail(kullaniciRequest.getEmail());
        kullanici.setSifre(kullaniciRequest.getSifre());
        kullanici.setRol(kullaniciRequest.getRol());

        Kullanici savedKullanici = kullaniciRepository.save(kullanici);
        return ResponseEntity.ok(savedKullanici);
    }

    // Kullanıcıyı id ile bulma
    @GetMapping("/{id}")
    public ResponseEntity<Kullanici> getKullaniciById(@PathVariable Long id) {
        Optional<Kullanici> kullanici = kullaniciRepository.findById(id);
        return kullanici.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Tüm kullanıcıları listeleme
    @GetMapping("/all")
    public ResponseEntity<List<Kullanici>> getAllKullanici() {
        List<Kullanici> kullaniciList = kullaniciRepository.findAll();
        return ResponseEntity.ok(kullaniciList);
    }

    // Kullanıcıyı id ile silme
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKullaniciById(@PathVariable Long id) {
        kullaniciRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Kullanıcıyı email ile silme
    @DeleteMapping("/email/{email}")
    public ResponseEntity<Void> deleteKullaniciByEmail(@PathVariable String email) {
        kullaniciRepository.deleteByEmail(email);
        return ResponseEntity.noContent().build();
    }

    // Kullanıcıyı email ile güncelleme
    @PutMapping("/{id}/email")
    public ResponseEntity<Void> updateEmailById(@PathVariable Long id, @RequestBody String newEmail) {
        int updatedRows = kullaniciRepository.updateEmailById(id, newEmail);
        if (updatedRows > 0) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
