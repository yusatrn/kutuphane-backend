package com.library.repository;

import com.library.model.KitapDegerlendirme;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface KitapDegerlendirmeRepository extends MongoRepository<KitapDegerlendirme, String> {
    List<KitapDegerlendirme> findByKitapId(String kitapId);
    List<KitapDegerlendirme> findByUyeId(String uyeId);
    
    @Aggregation(pipeline = {
        "{ $match: { kitapId: ?0 } }",
        "{ $group: { _id: null, averagePuan: { $avg: '$puan' } } }"
    })
    Double findAveragePuanByKitapId(String kitapId);
} 