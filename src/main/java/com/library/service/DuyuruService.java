package com.library.service;

import com.library.model.Duyuru;
import com.library.repository.DuyuruRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DuyuruService {
    private final DuyuruRepository duyuruRepository;
    
    public Duyuru duyuruEkle(Duyuru duyuru) {
        duyuru.setYayinTarihi(LocalDateTime.now());
        return duyuruRepository.save(duyuru);
    }
    
    public List<Duyuru> aktifDuyurular() {
        return duyuruRepository.findBySonGecerlilikTarihiGreaterThan(LocalDateTime.now());
    }
    
    public List<Duyuru> duyuruTipineGore(Duyuru.DuyuruTipi tip) {
        return duyuruRepository.findByTip(tip);
    }
} 