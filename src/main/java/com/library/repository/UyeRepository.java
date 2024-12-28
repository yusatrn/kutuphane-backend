package com.library.repository;

import com.library.model.Uye;
import com.library.model.Rol;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UyeRepository extends MongoRepository<Uye, String> {
    Optional<Uye> findByEposta(String eposta);
    List<Uye> findByRoller(Rol rol);
    List<Uye> findByAktif(Boolean aktif);
} 