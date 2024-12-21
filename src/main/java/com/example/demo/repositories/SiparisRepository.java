package com.example.demo.repositories;

import com.example.demo.entites.Siparis;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SiparisRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Siparis> findById(Long id) {
        Siparis siparis = entityManager.find(Siparis.class, id);
        return Optional.ofNullable(siparis);
    }

    public List<Siparis> findAll() {
        return entityManager.createQuery("SELECT s FROM Siparis s", Siparis.class).getResultList();
    }

    @Transactional
    public Siparis save(Siparis siparis) {
        if (siparis.getId() == null) {
            entityManager.persist(siparis);
        } else {
            entityManager.merge(siparis);
        }
        return siparis;
    }

    @Transactional
    public void deleteById(Long id) {
        Siparis siparis = entityManager.find(Siparis.class, id);
        if (siparis != null) {
            entityManager.remove(siparis);
        }
    }
}
