package com.example.demo.repositories;

import com.example.demo.entites.SiparisDetay;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SiparisDetayRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<SiparisDetay> findById(Long id) {
        SiparisDetay siparisDetay = entityManager.find(SiparisDetay.class, id);
        return Optional.ofNullable(siparisDetay);
    }

    public List<SiparisDetay> findAll() {
        return entityManager.createQuery("SELECT s FROM SiparisDetay s", SiparisDetay.class).getResultList();
    }

    @Transactional
    public SiparisDetay save(SiparisDetay siparisDetay) {
        if (siparisDetay.getId() == null) {
            entityManager.persist(siparisDetay);
        } else {
            entityManager.merge(siparisDetay);
        }
        return siparisDetay;
    }

    @Transactional
    public void deleteById(Long id) {
        SiparisDetay siparisDetay = entityManager.find(SiparisDetay.class, id);
        if (siparisDetay != null) {
            entityManager.remove(siparisDetay);
        }
    }
}
