package com.library.service;

import com.library.model.KitapRezervasyon;
import com.library.repository.KitapRezervasyonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RezervasyonService {
    private final KitapRezervasyonRepository rezervasyonRepository;
    
    public KitapRezervasyon rezervasyonOlustur(KitapRezervasyon rezervasyon) {
        rezervasyon.setRezervasyonTarihi(LocalDateTime.now());
        rezervasyon.setDurum(KitapRezervasyon.RezvasyonDurumu.BEKLEMEDE);
        return rezervasyonRepository.save(rezervasyon);
    }
    
    public List<KitapRezervasyon> kitabinRezervasyonlari(String kitapId) {
        return rezervasyonRepository.findByKitapId(kitapId);
    }
    
    public List<KitapRezervasyon> uyeninRezervasyonlari(String uyeId) {
        return rezervasyonRepository.findByUyeId(uyeId);
    }
} 