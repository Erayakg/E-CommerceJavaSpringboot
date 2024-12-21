package com.example.demo.repositories;

import com.example.demo.entites.Mesajlar;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MesajlarRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // Tüm mesajları listeleme
    public List<Mesajlar> findAll() {
        return entityManager.createQuery("SELECT m FROM Mesajlar m", Mesajlar.class)
                .getResultList();
    }

    // ID ile tek bir Mesajlar bulma
    public Optional<Mesajlar> findById(Long id) {
        Mesajlar mesajlar = entityManager.find(Mesajlar.class, id);
        return Optional.ofNullable(mesajlar);
    }

    // Mesaj ekleme veya güncelleme
    @Transactional
    public Mesajlar save(Mesajlar mesaj) {
        if (mesaj.getId() == null) {
            entityManager.persist(mesaj);
        } else {
            entityManager.merge(mesaj);
        }
        return mesaj;
    }

    // ID ile silme
    @Transactional
    public void deleteById(Long id) {
        Mesajlar mesaj = entityManager.find(Mesajlar.class, id);
        if (mesaj != null) {
            entityManager.remove(mesaj);
        }
    }

    // Dilerseniz "findAllWithKullanici" gibi bir JOIN FETCH ekleyebilirsiniz:
    public List<Mesajlar> findAllWithKullanici() {
        return entityManager.createQuery("""
                SELECT m FROM Mesajlar m
                LEFT JOIN FETCH m.kullanici k
                """, Mesajlar.class)
                .getResultList();
    }
}
