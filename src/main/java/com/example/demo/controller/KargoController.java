//package com.example.demo.controller;
//
//import com.example.demo.dto.KargoRequest;
//import com.example.demo.entites.Kargo;
//import com.example.demo.entites.Siparis;
//import com.example.demo.repositories.KargoRepository;
//import com.example.demo.repositories.SiparisRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import jakarta.validation.Valid;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/kargo")
//public class KargoController {
//
//    @Autowired
//    private KargoRepository kargoRepository;
//
//    @Autowired
//   // private SiparisRepository siparisRepository;
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Kargo> getKargoById(@PathVariable Long id) {
//        Optional<Kargo> kargo = kargoRepository.findById(id);
//        return kargo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @GetMapping("/all")
//    public ResponseEntity<List<Kargo>> getAllKargo() {
//        List<Kargo> kargoList = kargoRepository.findAll();
//        return ResponseEntity.ok(kargoList);
//    }
//
//    @GetMapping("/siparis/{siparisId}")
//    public ResponseEntity<List<Kargo>> getKargoBySiparisId(@PathVariable Long siparisId) {
//        List<Kargo> kargoList = kargoRepository.findBySiparisId(siparisId);
//        return ResponseEntity.ok(kargoList);
//    }
//
//    @PostMapping
//    public ResponseEntity<Kargo> createOrUpdateKargo(@Valid @RequestBody KargoRequest kargoRequest) {
//        Kargo kargo = new Kargo();
//        kargo.setDurum(kargoRequest.getDurum());
//        kargo.setTakipNo(kargoRequest.getTakipNo());
//        kargo.setTarih(kargoRequest.getTarih());
//
//        if (kargoRequest.getSiparisId() != null) {
//            Optional<Siparis> siparis = siparisRepository.findById(kargoRequest.getSiparisId());
//            siparis.ifPresent(kargo::setSiparis);
//        }
//
//        Kargo savedKargo = kargoRepository.save(kargo);
//        return ResponseEntity.ok(savedKargo);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteKargoById(@PathVariable Long id) {
//        kargoRepository.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }
//}
