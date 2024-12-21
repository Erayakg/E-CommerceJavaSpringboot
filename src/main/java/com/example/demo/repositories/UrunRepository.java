package com.example.demo.repositories;

import com.example.demo.entites.Urun;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class UrunRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Urun save(Urun urun) {
        if (urun.getId() == null) {
            entityManager.persist(urun);
            return urun;
        } else {
            return entityManager.merge(urun);
        }
    }

    public Optional<Urun> findById(Long id) {
        Urun urun = entityManager.find(Urun.class, id);
        return urun != null ? Optional.of(urun) : Optional.empty();
    }

    public List<Urun> findAll() {
        return entityManager.createQuery("SELECT u FROM Urun u", Urun.class).getResultList();
    }

    @Transactional
    public void deleteById(Long id) {
        Urun urun = entityManager.find(Urun.class, id);
        if (urun != null) {
            entityManager.remove(urun);
        }
    }
}

