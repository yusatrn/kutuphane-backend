package com.library.repository;

import com.library.model.Duyuru;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DuyuruRepository extends MongoRepository<Duyuru, String> {
    List<Duyuru> findByTip(Duyuru.DuyuruTipi tip);
    List<Duyuru> findBySonGecerlilikTarihiGreaterThan(LocalDateTime tarih);
} 