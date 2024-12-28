package com.library.service;

import com.library.model.UyeAktivite;
import com.library.repository.UyeAktiviteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UyeAktiviteService {
    private final UyeAktiviteRepository aktiviteRepository;
    
    public UyeAktivite aktiviteKaydet(String uyeId, String islemTipi, String kitapId, String detay) {
        UyeAktivite aktivite = new UyeAktivite();
        aktivite.setUyeId(uyeId);
        aktivite.setIslemTipi(islemTipi);
        aktivite.setKitapId(kitapId);
        aktivite.setDetay(detay);
        aktivite.setIslemTarihi(LocalDateTime.now());
        return aktiviteRepository.save(aktivite);
    }
    
    public List<UyeAktivite> uyeninAktiviteleri(String uyeId) {
        return aktiviteRepository.findByUyeId(uyeId);
    }
    
    public List<UyeAktivite> uyeninTarihAraligiAktiviteleri(String uyeId, LocalDateTime baslangic, LocalDateTime bitis) {
        return aktiviteRepository.findByUyeIdAndTarihAraligi(uyeId, baslangic, bitis);
    }
} 