package com.example.demo.repositories;

import com.example.demo.entites.Tedarikci;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TedarikciRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // Tüm tedarikçileri listeleme
    public List<Tedarikci> findAll() {
        return entityManager.createQuery("SELECT t FROM Tedarikci t", Tedarikci.class)
                .getResultList();
    }

    // ID ile tek bir tedarikçi bulma
    public Optional<Tedarikci> findById(Long id) {
        Tedarikci tedarikci = entityManager.find(Tedarikci.class, id);
        return Optional.ofNullable(tedarikci);
    }

    // Tedarikçi ekleme veya güncelleme
    @Transactional
    public Tedarikci save(Tedarikci tedarikci) {
        if (tedarikci.getId() == null) {
            entityManager.persist(tedarikci);
        } else {
            entityManager.merge(tedarikci);
        }
        return tedarikci;
    }

    // ID ile tedarikçi silme
    @Transactional
    public void deleteById(Long id) {
        Tedarikci tedarikci = entityManager.find(Tedarikci.class, id);
        if (tedarikci != null) {
            entityManager.remove(tedarikci);
        }
    }

    // Tedarikçileri ve ilişkili UrunTedarikci'leri tek seferde getirme
    public List<Tedarikci> findAllWithUrunTedarikci() {
        return entityManager.createQuery("""
                SELECT t FROM Tedarikci t
                LEFT JOIN FETCH t.urunTedarikciList ut
                """, Tedarikci.class)
                .getResultList();
    }
}
