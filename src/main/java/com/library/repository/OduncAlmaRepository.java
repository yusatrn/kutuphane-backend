package com.library.repository;

import com.library.model.OduncAlma;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OduncAlmaRepository extends MongoRepository<OduncAlma, String> {
    List<OduncAlma> findByUyeId(String uyeId);
    List<OduncAlma> findByIadeedildi(Boolean iadeDurumu);
    List<OduncAlma> findByUyeIdAndIadeedildi(String uyeId, Boolean iadeDurumu);
    
    @Aggregation(pipeline = {
        "{ $group: { _id: '$kitapId', toplamOdunc: { $sum: 1 } } }",
        "{ $sort: { toplamOdunc: -1 } }",
        "{ $limit: 10 }"
    })
    List<Object> enCokOduncAlinanKitaplar();
    
    @Aggregation(pipeline = {
        "{ $match: { teslimTarihi: null } }",
        "{ $group: { _id: '$uyeId', gecikmisKitapSayisi: { $sum: 1 } } }"
    })
    List<Object> gecikmisKitapRaporu();
} 