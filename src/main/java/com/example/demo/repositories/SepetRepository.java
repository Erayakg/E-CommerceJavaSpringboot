package com.example.demo.repositories;

import com.example.demo.entites.Sepet;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SepetRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Sepet> findById(Long id) {
        Sepet sepet = entityManager.find(Sepet.class, id);
        return Optional.ofNullable(sepet);
    }

    public List<Sepet> findAll() {
        return entityManager.createQuery("SELECT s FROM Sepet s", Sepet.class).getResultList();
    }

    @Transactional
    public Sepet save(Sepet sepet) {
        if (sepet.getId() == null) {
            entityManager.persist(sepet);
        } else {
            entityManager.merge(sepet);
        }
        return sepet;
    }

    @Transactional
    public void deleteById(Long id) {
        Sepet sepet = entityManager.find(Sepet.class, id);
        if (sepet != null) {
            entityManager.remove(sepet);
        }
    }
}
