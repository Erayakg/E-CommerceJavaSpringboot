package com.example.demo.repositories;

import com.example.demo.entites.Indirim;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class IndirimRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Indirim> findById(Long id) {
        Indirim indirim = entityManager.find(Indirim.class, id);
        return Optional.ofNullable(indirim);
    }

    public List<Indirim> findAll() {
        return entityManager.createQuery("SELECT i FROM Indirim i", Indirim.class).getResultList();
    }

    @Transactional
    public Indirim save(Indirim indirim) {
        if (indirim.getId() == null) {
            entityManager.persist(indirim);
        } else {
            entityManager.merge(indirim);
        }
        return indirim;
    }

    @Transactional
    public void deleteById(Long id) {
        Indirim indirim = entityManager.find(Indirim.class, id);
        if (indirim != null) {
            entityManager.remove(indirim);
        }
    }
}
