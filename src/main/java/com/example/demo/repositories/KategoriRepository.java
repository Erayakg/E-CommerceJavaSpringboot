package com.example.demo.repositories;

import com.example.demo.entites.Kategori;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class KategoriRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Kategori> findById(Long id) {
        Kategori kategori = entityManager.find(Kategori.class, id);
        return Optional.ofNullable(kategori);
    }

    public List<Kategori> findAll() {
        return entityManager.createQuery("SELECT k FROM Kategori k", Kategori.class).getResultList();
    }

    @Transactional
    public Kategori save(Kategori kategori) {
        if (kategori.getId() == null) {
            entityManager.persist(kategori);
        } else {
            entityManager.merge(kategori);
        }
        return kategori;
    }

    @Transactional
    public void deleteById(Long id) {
        Kategori kategori = entityManager.find(Kategori.class, id);
        if (kategori != null) {
            entityManager.remove(kategori);
        }
    }
}