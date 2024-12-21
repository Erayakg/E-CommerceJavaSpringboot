package com.example.demo.repositories;

import com.example.demo.entites.Yoneticiler;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class YoneticilerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Tüm Yoneticiler'i getirir.
     * Dilerseniz lazy/eager farkına göre ek fetch yazabilirsiniz.
     */
    public List<Yoneticiler> findAll() {
        return entityManager
                .createQuery("SELECT y FROM Yoneticiler y", Yoneticiler.class)
                .getResultList();
    }

    /**
     * Yoneticiler ve ilişkili Kullanici'sını tek seferde çekmek isteyenler için
     * LEFT JOIN FETCH örneği:
     */
    public List<Yoneticiler> findAllWithKullanici() {
        return entityManager.createQuery("""
                SELECT y FROM Yoneticiler y
                LEFT JOIN FETCH y.kullanici k
                """, Yoneticiler.class)
                .getResultList();
    }

    /**
     * ID ile tek bir Yoneticiler bulma
     */
    public Optional<Yoneticiler> findById(Long id) {
        Yoneticiler yoneticiler = entityManager.find(Yoneticiler.class, id);
        return Optional.ofNullable(yoneticiler);
    }

    /**
     * Ekleme/Güncelleme
     */
    @Transactional
    public Yoneticiler save(Yoneticiler yoneticiler) {
        if (yoneticiler.getId() == null) {
            // yeni kayıt
            entityManager.persist(yoneticiler);
        } else {
            // güncelleme
            entityManager.merge(yoneticiler);
        }
        return yoneticiler;
    }

    /**
     * Silme
     */
    @Transactional
    public void deleteById(Long id) {
        Yoneticiler yoneticiler = entityManager.find(Yoneticiler.class, id);
        if (yoneticiler != null) {
            entityManager.remove(yoneticiler);
        }
    }
}
