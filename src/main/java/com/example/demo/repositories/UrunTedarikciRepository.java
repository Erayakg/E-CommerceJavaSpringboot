package com.example.demo.repositories;

import com.example.demo.entites.UrunTedarikci;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UrunTedarikciRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // Tüm UrunTedarikci kayıtlarını listeleme
    public List<UrunTedarikci> findAll() {
        return entityManager.createQuery("SELECT ut FROM UrunTedarikci ut", UrunTedarikci.class)
                .getResultList();
    }

    // ID ile tek bir UrunTedarikci bulma
    public Optional<UrunTedarikci> findById(Long id) {
        UrunTedarikci urunTedarikci = entityManager.find(UrunTedarikci.class, id);
        return Optional.ofNullable(urunTedarikci);
    }

    // UrunTedarikci kaydı ekleme/güncelleme
    @Transactional
    public UrunTedarikci save(UrunTedarikci urunTedarikci) {
        if (urunTedarikci.getId() == null) {
            entityManager.persist(urunTedarikci);
        } else {
            entityManager.merge(urunTedarikci);
        }
        return urunTedarikci;
    }

    // ID ile UrunTedarikci silme
    @Transactional
    public void deleteById(Long id) {
        UrunTedarikci urunTedarikci = entityManager.find(UrunTedarikci.class, id);
        if (urunTedarikci != null) {
            entityManager.remove(urunTedarikci);
        }
    }
}
