package com.library.repository;

import com.library.model.Kitap;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface KitapRepository extends MongoRepository<Kitap, String> {
    @Query("{ $text: { $search: ?0 } }")
    List<Kitap> fullTextSearch(String searchText);
    
    @Query("{ 'yazar': { $regex: ?0, $options: 'i' } }")
    List<Kitap> findByYazarContainingIgnoreCase(String yazar);
    
    @Query("{ kategoriler: ?0, stokDurumu: { $gt: 0 } }")
    List<Kitap> findByKategoriAndStokta(String kategori);
    
    @Query("{ yayinYili: { $gte: ?0, $lte: ?1 } }")
    List<Kitap> findByYayinYiliBetween(int baslangic, int bitis);
    
    Optional<Kitap> findByIsbn(String isbn);
    
    @Query("{ 'ad': { $regex: ?0, $options: 'i' } }")
    List<Kitap> findByAdContainingIgnoreCase(String ad);
} 