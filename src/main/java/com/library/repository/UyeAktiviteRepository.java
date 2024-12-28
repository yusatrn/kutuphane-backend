package com.library.repository;

import com.library.model.UyeAktivite;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UyeAktiviteRepository extends MongoRepository<UyeAktivite, String> {
    List<UyeAktivite> findByUyeId(String uyeId);
    
    @Query("{ 'uyeId': ?0, 'islemTarihi': { $gte: ?1, $lte: ?2 } }")
    List<UyeAktivite> findByUyeIdAndTarihAraligi(String uyeId, LocalDateTime baslangic, LocalDateTime bitis);
    
    List<UyeAktivite> findByIslemTipi(String islemTipi);
} 