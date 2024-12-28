package com.library.repository;

import com.library.model.KitapRezervasyon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KitapRezervasyonRepository extends MongoRepository<KitapRezervasyon, String> {
    List<KitapRezervasyon> findByUyeId(String uyeId);
    List<KitapRezervasyon> findByKitapId(String kitapId);
    List<KitapRezervasyon> findByDurum(KitapRezervasyon.RezvasyonDurumu durum);
} 