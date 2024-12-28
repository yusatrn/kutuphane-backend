package com.library.repository;

import com.library.model.Bildirim;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BildirimRepository extends MongoRepository<Bildirim, String> {
    List<Bildirim> findByUyeIdAndOkundu(String uyeId, boolean okundu);
    List<Bildirim> findByUyeIdAndTip(String uyeId, Bildirim.BildirimTipi tip);
    long countByUyeIdAndOkundu(String uyeId, boolean okundu);
} 