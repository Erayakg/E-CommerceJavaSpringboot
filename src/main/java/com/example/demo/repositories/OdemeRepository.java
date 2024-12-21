package com.example.demo.repositories;

import com.example.demo.entites.Odeme;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OdemeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Odeme> findById(Long id) {
        Odeme odeme = entityManager.find(Odeme.class, id);
        return Optional.ofNullable(odeme);
    }

    public List<Odeme> findAll() {
        return entityManager.createQuery("SELECT o FROM Odeme o", Odeme.class).getResultList();
    }

    @Transactional
    public Odeme save(Odeme odeme) {
        if (odeme.getId() == null) {
            entityManager.persist(odeme);
        } else {
            entityManager.merge(odeme);
        }
        return odeme;
    }

    @Transactional
    public void deleteById(Long id) {
        Odeme odeme = entityManager.find(Odeme.class, id);
        if (odeme != null) {
            entityManager.remove(odeme);
        }
    }
}
