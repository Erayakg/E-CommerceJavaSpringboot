package com.example.demo.repositories;

import com.example.demo.entites.Kullanici;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class KullaniciRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // Kullanıcıyı email adresi ile bulma
    public Optional<Kullanici> findByEmail(String email) {
        List<Kullanici> result = entityManager.createQuery("""
                SELECT k FROM Kullanici k WHERE k.email = :email
                """, Kullanici.class)
                .setParameter("email", email)
                .getResultList();

        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

    // Tüm kullanıcıları ve Mesajlar'ı ilişkilendirilmiş olarak getirme

    public List<Kullanici> findAllWithMesajlar() {
        return entityManager.createQuery("""
                SELECT k FROM Kullanici k
                LEFT JOIN FETCH k.mesajlar m
                """, Kullanici.class)
                .getResultList();
    }

    // Tüm kullanıcıları ve Favoriler'i ilişkilendirilmiş olarak getirme
    public List<Kullanici> findAllWithFavoriler() {
        return entityManager.createQuery("""
                SELECT k FROM Kullanici k
                LEFT JOIN FETCH k.favoriler f
                """, Kullanici.class)
                .getResultList();
    }

    // Tüm kullanıcıları ve Adresler'i ilişkilendirilmiş olarak getirme
    public List<Kullanici> findAllWithAdresler() {
        return entityManager.createQuery("""
                SELECT k FROM Kullanici k
                LEFT JOIN FETCH k.adresler a
                """, Kullanici.class)
                .getResultList();
    }


    // Kullanıcıyı eklemek (Insert)
    @Transactional
    public Kullanici save(Kullanici kullanici) {
        if (kullanici.getId() == null) {

            entityManager.persist(kullanici);  // Yeni kullanıcı ekleme
        } else {
            entityManager.merge(kullanici);  // Mevcut kullanıcıyı güncelleme
        }
        return kullanici;
    }

    // Kullanıcıyı id ile bulmak (Read)
    public Optional<Kullanici> findById(Long id) {
        Kullanici kullanici = entityManager.find(Kullanici.class, id);
        return Optional.ofNullable(kullanici);
    }

    // Tüm kullanıcıları listelemek (Read)
    public List<Kullanici> findAll() {
        return entityManager.createQuery("SELECT k FROM Kullanici k", Kullanici.class).getResultList();
    }

    // Kullanıcıyı id ile silmek (Delete)
    @Transactional
    public void deleteById(Long id) {
        Kullanici kullanici = entityManager.find(Kullanici.class, id);
        if (kullanici != null) {
            entityManager.remove(kullanici);
        }
    }

    // Kullanıcıyı email ile bulup silmek
    @Transactional
    public void deleteByEmail(String email) {
        entityManager.createQuery("DELETE FROM Kullanici k WHERE k.email = :email")
                .setParameter("email", email)
                .executeUpdate();
    }

    // Kullanıcıyı email ile güncellemek
    @Transactional
    public int updateEmailById(Long id, String newEmail) {
        return entityManager.createQuery("UPDATE Kullanici k SET k.email = :newEmail WHERE k.id = :id")
                .setParameter("newEmail", newEmail)
                .setParameter("id", id)
                .executeUpdate();
    }

}
