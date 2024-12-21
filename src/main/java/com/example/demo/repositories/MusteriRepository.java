package com.example.demo.repositories;

import com.example.demo.entites.Musteri;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MusteriRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Musteri> findById(Long id) {
        Musteri musteri = entityManager.find(Musteri.class, id);
        return Optional.ofNullable(musteri);
    }

    public List<Musteri> findAll() {
        return entityManager.createQuery("SELECT m FROM Musteri m", Musteri.class).getResultList();
    }

    @Transactional
    public Musteri save(Musteri musteri) {
        if (musteri.getId() == null) {
            entityManager.persist(musteri);
        } else {
            entityManager.merge(musteri);
        }
        return musteri;
    }

    @Transactional
    public void deleteById(Long id) {
        Musteri musteri = entityManager.find(Musteri.class, id);
        if (musteri != null) {
            entityManager.remove(musteri);
        }
    }
}
