package com.library.repository;

import com.library.model.Kategori;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KategoriRepository extends MongoRepository<Kategori, String> {
} 