package com.example.demo.repositories;

import com.example.demo.entites.Kargo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class KargoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Kargo> findById(Long id) {
        Kargo kargo = entityManager.find(Kargo.class, id);
        return Optional.ofNullable(kargo);
    }

    public List<Kargo> findAll() {
        return entityManager.createQuery("SELECT k FROM Kargo k", Kargo.class).getResultList();
    }

    @Transactional
    public Kargo save(Kargo kargo) {
        if (kargo.getId() == null) {
            entityManager.persist(kargo);
        } else {
            entityManager.merge(kargo);
        }
        return kargo;
    }

    @Transactional
    public void deleteById(Long id) {
        Kargo kargo = entityManager.find(Kargo.class, id);
        if (kargo != null) {
            entityManager.remove(kargo);
        }
    }

    public List<Kargo> findBySiparisId(Long siparisId) {
        return entityManager.createQuery("""
                SELECT k FROM Kargo k WHERE k.siparis.id = :siparisId
                """, Kargo.class)
                .setParameter("siparisId", siparisId)
                .getResultList();
    }
}
