package com.library.service;

import com.library.model.KitapDegerlendirme;
import com.library.repository.KitapDegerlendirmeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DegerlendirmeService {
    private final KitapDegerlendirmeRepository degerlendirmeRepository;
    
    public KitapDegerlendirme degerlendirmeEkle(KitapDegerlendirme degerlendirme) {
        degerlendirme.setDegerlendirmeTarihi(LocalDateTime.now());
        return degerlendirmeRepository.save(degerlendirme);
    }
    
    public List<KitapDegerlendirme> kitabinDegerlendirmeleri(String kitapId) {
        return degerlendirmeRepository.findByKitapId(kitapId);
    }
    
    public Double kitabinOrtalamaPuani(String kitapId) {
        return degerlendirmeRepository.findAveragePuanByKitapId(kitapId);
    }
} 