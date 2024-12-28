package com.library.repository;

import com.library.model.CezaKaydi;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CezaKaydiRepository extends MongoRepository<CezaKaydi, String> {
    List<CezaKaydi> findByUyeId(String uyeId);
    List<CezaKaydi> findByOdendi(boolean odendi);
} 